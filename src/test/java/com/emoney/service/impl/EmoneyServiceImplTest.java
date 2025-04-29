package com.emoney.service.impl;

import com.emoney.domain.dto.request.RequestEmoneyDeductDto;
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
import static org.mockito.Mockito.when;

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

        RequestEmoneyDeductDto dto = RequestEmoneyDeductDto.builder()
                .userSeq(1L)
                .build();

        when(emoneyRepository.findAllUsableEmoneyList(dto)).thenReturn(Arrays.asList(emoney1, emoney2));

        // When
        List<Emoney> emoneyList = emoneyRepository.findAllUsableEmoneyList(dto);
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

    @Test
    void useCancelEmoney_적립금_취소_테스트(){
        // Given
        Long expectEmonayAmonut = 1400L;

        Emoney emoney = Emoney.builder()
                .amount(500L)
                .usageAmount(100L)
                .remainAmount(400L)
                .build();

        Emoney cancelEmoney = Emoney.builder()
                .amount(1000L)
                .usageAmount(0L)
                .remainAmount(1000L)
                .build();

        RequestEmoneyDeductDto dto = RequestEmoneyDeductDto.builder()
                .userSeq(1L)
                .build();

        when(emoneyRepository.findAllUsableEmoneyList(dto)).thenReturn(Arrays.asList(emoney, cancelEmoney));

        // When
        emoneyRepository.save(cancelEmoney);
        Long totalEmoneyAmount = emoneyRepository.findAllUsableEmoneyList(dto).stream()
                .mapToLong(Emoney::getRemainAmount).sum();

        // Then
        assertEquals(expectEmonayAmonut, totalEmoneyAmount);
    }
}
