package com.emoney.service.impl;

import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.dto.EmoneyExtendDto;
import com.emoney.domain.dto.EmoneyUpdateDto;
import com.emoney.domain.dto.EmoneyUsageDto;
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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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
        emoney.setUsageAmonut(0L);
        emoney.setRemainAmount(emoneyCreateDto.getAmount());

        emoneyRepository.save(emoney);
    }

    @Override
    public void deductEmoney(Long emoneySeq) {

    }

    @Override
    @Transactional
    public void useEmoney(EmoneyUsageDto emoneyUsageDto) {
        Long userSeq = emoneyUsageDto.getUserSeq();
        Long emoneyRequestAmount = emoneyUsageDto.getAmount();

        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime zoneSeoulDateTime = ZonedDateTime.now(zoneId);
        LocalDateTime localDateTime = zoneSeoulDateTime.toLocalDateTime();
        emoneyUsageDto.setSearchDateTime(localDateTime);

        // 1. 사용 요청한 적립금이 사용 가능 잔액 누적 적립금 보다 큰지 비교(적립금 잔액 검사)
        List<Emoney> emoneyList = emoneyRepository.findAllUsableEmoneyList(emoneyUsageDto);
        Long totalRemainAmount = emoneyList.stream()
                .map(Emoney::getRemainAmount)
                .reduce(0L, Long::sum);

        if(emoneyRequestAmount > totalRemainAmount){
            throw new RuntimeException("사용 요청한 적립금이 사용 가능 잔액 누적 적립금 보다 커서 불가합니다.");
        }

        // 2. 적립금 사용
        for(Emoney emoney : emoneyList){
            Long emoneyRemainAmount = emoney.getRemainAmount();

            /**
             * 2-1. 사용 요청한 적립금이 현재 적립금 보다 크면, 사용한 적립금을 현재 적립금으로 처리 및 현재 잔액 적립금을 0원으로 처리
             *      반대로 작거나 같으면, 사용한 적립금을 사용 요청한 적립금으로 처리 및 현재 잔액 적립금에서 사용 요청한 적립금을 차감 처리
             */
            Long emoneyUsageAmount = emoneyRequestAmount > emoneyRemainAmount ? emoneyRemainAmount : emoneyRequestAmount;

            // 2-2. 현재 적립금 수정
            emoney.setUsageAmonut(emoney.getUsageAmonut() + emoneyUsageAmount);
            emoney.setRemainAmount(emoney.getRemainAmount() - emoneyUsageAmount);
            emoneyRepository.save(emoney);
            
            // 2-3. 적립금 사용내역 등록
            emoneyUsageHistoryRepository.save(
                    EmoneyUsageHistory.builder()
                            .usageTypeSeq(emoneyUsageDto.getUsageTypeSeq())
                            .usageAmount(emoneyUsageAmount)
                            .content(emoneyUsageDto.getContent())
                            .emoney(emoney)
                            .creationDate(localDateTime)
                            .build()
            );

            // 2-4. 사용 요청한 적립금 차감
            emoneyRequestAmount = emoneyRequestAmount - emoneyUsageAmount;

            // 2-5. 사용 요청한 적립금 다 사용한 경우 루프 종료
            if(emoneyRequestAmount <= 0){
                break;
            }
        }

        // 3. 사용 요청한 적립금 등록
        emoneyRepository.save(
                Emoney.builder()
                        .userSeq(userSeq)
                        .orderSeq(emoneyUsageDto.getOrderSeq())
                        .typeSeq(emoneyUsageDto.getUsageTypeSeq())
                        .amount(emoneyUsageDto.getAmount())
                        .usageAmonut(emoneyUsageDto.getAmount())
                        .remainAmount(0L)
                        .isApproved(true)
                        .content(emoneyUsageDto.getContent())
                        .creationDate(localDateTime)
                        .build()
        );
    }

    @Override
    public void useCancelEmoney(EmoneyUpdateDto emoneyUpdateDto) {

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
