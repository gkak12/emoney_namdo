package com.emoney.service.impl;

import com.emoney.comm.DateTimeUtil;
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
            throw new RuntimeException("사용 요청한 적립금이 사용 가능 잔액 누적 적립금 보다 커서 불가합니다.");
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

        // 적립금 취소하면 기존에 사용한 적립금 원복하는게 아니라 새로 적립금 발급
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
    public void approveEmoney(Long emoneySeq) {

    }

    @Override
    public void refuseEmoney(Long emoneySeq) {

    }

    @Override
    public void extendEmoney(EmoneyExtendDto emoneyExtendDto) {

    }

    @Override
    public void expireEmoney() {

    }
}
