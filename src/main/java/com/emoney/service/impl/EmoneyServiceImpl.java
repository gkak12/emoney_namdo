package com.emoney.service.impl;

import com.emoney.comm.enums.EmoneyErrorEnums;
import com.emoney.comm.exception.EmoneyException;
import com.emoney.comm.util.DateTimeUtil;
import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.dto.EmoneyExtendDto;
import com.emoney.domain.dto.EmoneyDeductDto;
import com.emoney.domain.entity.Emoney;
import com.emoney.domain.entity.EmoneyUsageHistory;
import com.emoney.domain.mapper.EmoneyMapper;
import com.emoney.domain.vo.EmoneyVo;
import com.emoney.repository.EmoneyRepository;
import com.emoney.repository.EmoneyUsageHistoryRepository;
import com.emoney.service.EmoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmoneyServiceImpl implements EmoneyService {

    private final EmoneyMapper emoneyMapper;

    private final EmoneyRepository emoneyRepository;
    private final EmoneyUsageHistoryRepository emoneyUsageHistoryRepository;

    @Override
    public List<EmoneyVo> findAllEmoneys() {
        return emoneyRepository.findAll().stream()
                .map(emoneyMapper::toVo)
                .toList();
    }

    @Override
    @Transactional
    public void createEmoney(EmoneyCreateDto emoneyCreateDto) {
        Emoney emoney = emoneyMapper.toCreateEntity(emoneyCreateDto);
        emoneyRepository.save(emoney);
    }

    @Override
    @Transactional
    public void deductEmoney(EmoneyDeductDto emoneyDeductDto) {
        // 1. 적립금 사용/차감 요청 정보 세팅
        Long userSeq = emoneyDeductDto.getUserSeq();
        Long emoneyRequestAmount = emoneyDeductDto.getAmount();
        LocalDateTime localDateTime = DateTimeUtil.getLocalDateTime();
        emoneyDeductDto.setSearchDateTime(localDateTime);

        // 2. 사용/차감 가능 적립금 조회(적립금 만료일이 빠른 순서대로 정렬)
        List<Emoney> emoneyList = emoneyRepository.findAllUsableEmoneyList(emoneyDeductDto);

        // 3. 사용/차감 요청한 적립금이 사용/차감 가능 적립금 잔액 누적 적립금 보다 큰지 비교(적립금 잔액 검사)
        Long totalRemainAmount = emoneyList.stream()
                .map(Emoney::getRemainAmount)
                .reduce(0L, Long::sum);

        if(emoneyRequestAmount > totalRemainAmount){
            throw new EmoneyException(EmoneyErrorEnums.BAD_REQUEST, "사용 요청한 적립금이 사용 가능 잔액 누적 적립금 보다 커서 불가합니다.");
        }

        // 4. 적립금 사용/차감
        for(Emoney emoney : emoneyList){
            Long emoneyRemainAmount = emoney.getRemainAmount();

            /**
             * 4-1. 사용/차감 요청한 적립금이 현재 잔액 적립금 보다 크면, 사용한 적립금을 현재 잔액 적립금으로 처리
             *      반대로 작거나 같으면, 사용한 적립금을 사용 요청한 적립금으로 처리
             */
            Long emoneyUsageAmount = emoneyRequestAmount > emoneyRemainAmount ? emoneyRemainAmount : emoneyRequestAmount;

            /**
             * 4-2. 현재 적립금 정보 수정
             *      현재 적립금의 사용/차감한 적립금과 잔액 적립금 누적 갱신
             */
            emoney.setUsageAmount(emoney.getUsageAmount() + emoneyUsageAmount);
            emoney.setRemainAmount(emoney.getRemainAmount() - emoneyUsageAmount);
            emoneyRepository.save(emoney);

            // 4-3. 적립금 사용/차감 내역 테이블 등록
            emoneyUsageHistoryRepository.save(
                    EmoneyUsageHistory.builder()
                            .usageTypeSeq(emoneyDeductDto.getUsageTypeSeq())
                            .usageAmount(emoneyUsageAmount)
                            .content(emoneyDeductDto.getContent())
                            .creationDate(localDateTime)
                            .emoney(emoney)
                            .build()
            );

            // 4-4. 사용/차감 요청한 적립금 차감
            emoneyRequestAmount = emoneyRequestAmount - emoneyUsageAmount;

            // 4-5. 사용/차감 요청한 적립금 다 사용한 경우 루프 종료
            if(emoneyRequestAmount <= 0){
                break;
            }
        }

        // 5. 사용/차감 요청한 적립금 정보 적립금 테이블 등록
        emoneyRepository.save(
                Emoney.builder()
                        .userSeq(userSeq)
                        .orderSeq(emoneyDeductDto.getOrderSeq())
                        .typeSeq(emoneyDeductDto.getTypeSeq())
                        .amount(emoneyDeductDto.getAmount())
                        .usageAmount(emoneyDeductDto.getAmount())
                        .remainAmount(0L)
                        .isApproved(true)
                        .content(emoneyDeductDto.getContent())
                        .creationDate(localDateTime)
                        .build()
        );
    }

    @Override
    @Transactional
    public void useCancelEmoney(EmoneyCancelDto emoneyCancelDto) {
        Map<String, Object> resultMap = emoneyRepository.findCancellationEmoney(emoneyCancelDto);
        LocalDateTime expirationDate = (LocalDateTime) resultMap.get("expirationDate");
        Long amount = (Long) resultMap.get("amount");

        // 적립금 취소하면 기존에 사용한 적립금 원복하는게 아니라 새로운 적립금 발급
        emoneyRepository.save(
                Emoney.builder()
                        .userSeq(emoneyCancelDto.getUserSeq())
                        .amount(amount)
                        .usageAmount(0L)
                        .remainAmount(amount)
                        .content(emoneyCancelDto.getContent())
                        .expirationDate(expirationDate)
                        .creationDate(DateTimeUtil.getLocalDateTime())
                        .build()
        );
    }

    @Override
    @Transactional
    public void approveEmoney(Long emoneySeq) {
        Emoney emoney = emoneyRepository.findById(emoneySeq)
                .orElseThrow(() -> new EmoneyException(EmoneyErrorEnums.NOT_FOUND, "승인 대상 적립금 존재하지 않습니다."));

        if(emoney.getIsApproved()){
            throw new EmoneyException(EmoneyErrorEnums.BAD_REQUEST, "이미 승인된 적립금입니다.");
        }

        emoney.approve();   // 승인 처리
        emoneyRepository.save(emoney);
    }

    @Override
    @Transactional
    public void rejectEmoney(Long emoneySeq) {
        Emoney emoney = emoneyRepository.findById(emoneySeq)
                .orElseThrow(() -> new EmoneyException(EmoneyErrorEnums.NOT_FOUND, "반려 대상 적립금 존재하지 않습니다."));

        if(!emoney.getIsApproved()){
            throw new EmoneyException(EmoneyErrorEnums.BAD_REQUEST, "이미 반려된 적립금입니다.");
        }

        emoney.reject();    // 반려 처리
        emoneyRepository.save(emoney);
    }

    @Override
    @Transactional
    public void extendEmoney(EmoneyExtendDto emoneyExtendDto) {
        LocalDateTime expirationDateTime = emoneyExtendDto.getExpirationDateTime();

        Emoney emoney = emoneyRepository.findById(emoneyExtendDto.getEmoneySeq())
                .orElseThrow(() -> new EmoneyException(EmoneyErrorEnums.NOT_FOUND, "연장 대상 적립금 존재하지 않습니다."));

        String workName = "연장";
        validateExpirationDate(emoney, expirationDateTime, workName);     // 만료일 체크
        validateApprovalStatus(emoney, workName);                         // 승인 상태 체크
        validateExpirationStatus(emoney, workName);                       // 만료 상태 체크

        emoney.extendExpirationTime(expirationDateTime);        // 만료일 연장
        emoneyRepository.save(emoney);
    }

    private void validateExpirationDate(Emoney emoney, LocalDateTime expirationDateTime, String workName) {
        if (emoney.getExpirationDate().isAfter(expirationDateTime)) {
            throw new EmoneyException(EmoneyErrorEnums.BAD_REQUEST, workName.concat(" 대상 적립금 만료일이 요청한 만료일보다 이후입니다."));
        }
    }

    private void validateApprovalStatus(Emoney emoney, String workName) {
        if (!emoney.getIsApproved()) {
            throw new EmoneyException(EmoneyErrorEnums.BAD_REQUEST, workName.concat(" 대상 적립금은 아직 승인되지 않았습니다."));
        }
    }

    private void validateExpirationStatus(Emoney emoney, String workName) {
        if (emoney.getIsExpired()) {
            throw new EmoneyException(EmoneyErrorEnums.BAD_REQUEST, workName.concat(" 대상 적립금은 이미 만료되었습니다."));
        }
    }

    @Override
    @Transactional
    public void expireEmoney(Long emoneySeq) {
        Emoney emoney = emoneyRepository.findById(emoneySeq)
                .orElseThrow(() -> new EmoneyException(EmoneyErrorEnums.NOT_FOUND, "만료 대상 적립금 존재하지 않습니다."));

        validateExpirationStatus(emoney,"만료");      // 만료 상태 체크

        emoney.expire();    // 만료 처리
        emoneyRepository.save(emoney);
    }
}
