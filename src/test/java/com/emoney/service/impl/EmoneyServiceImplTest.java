package com.emoney.service.impl;

import com.emoney.domain.entity.Emoney;
import com.emoney.repository.EmoneyRepository;
import com.emoney.repository.EmoneyUsageHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmoneyServiceImplTest {

    @Mock
    private EmoneyRepository emoneyRepository;

    @Mock
    private EmoneyUsageHistoryRepository emoneyUsageHistoryRepository;

    @Test
    void deductEmoney_적립금_사용_차감_잔액_확인_테스트() {
        // Given
        Boolean expectationBoolean = false;
        Long emoneyRequestAmount = 500L;

        Emoney emoney1 = Emoney.builder()
                .amount(500L)
                .usageAmount(200L)
                .remainAmount(300L)
                .build();

        Emoney emoney2 = Emoney.builder()
                .amount(300L)
                .usageAmount(0L)
                .remainAmount(300L)
                .build();

        List<Emoney> emoneyList = Arrays.asList(emoney1, emoney2);

        // When
        Long totalRemainAmount = emoneyList.stream().map(Emoney::getRemainAmount).reduce(0L, Long::sum);
        Boolean resultBoolean = emoneyRequestAmount > totalRemainAmount ? true : false;

        // Then
        assertEquals(expectationBoolean, resultBoolean);
    }

    @Test
    void deductEmoney_적립금_사용_차감_성공_테스트() {
        // Given
        Long expectationRemainAmount = 100L;
        Long emoneyRequestAmount = 500L;

        Emoney emoney1 = Emoney.builder()
                .amount(500L)
                .usageAmount(200L)
                .remainAmount(300L)
                .build();

        Emoney emoney2 = Emoney.builder()
                .amount(300L)
                .usageAmount(0L)
                .remainAmount(300L)
                .build();

        List<Emoney> emoneyList = Arrays.asList(emoney1, emoney2);

        // When
        for(Emoney emoney : emoneyList){
            Long emoneyRemainAmount = emoney.getRemainAmount();
            Long emoneyUsageAmount = emoneyRequestAmount > emoneyRemainAmount ? emoneyRemainAmount : emoneyRequestAmount;

            emoney.deduct(emoneyUsageAmount);

            emoneyRequestAmount = emoneyRequestAmount - emoneyUsageAmount;

            if(emoneyRequestAmount <= 0){
                break;
            }
        }

        // Then
        Long resultRemainAmount = emoneyList.stream().map(Emoney::getRemainAmount).reduce(0L, Long::sum);
        assertEquals(expectationRemainAmount, resultRemainAmount);
    }
}
