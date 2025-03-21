package com.emoney.service.impl;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.*;
import com.emoney.repository.EmoneyUsageHistoryRepository;
import com.emoney.service.EmoneyUsageHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmoneyUsageHistoryServiceImpl implements EmoneyUsageHistoryService {

    private final EmoneyUsageHistoryRepository emoneyUsageHistoryRepository;

    @Override
    public ResponseEmoneyLogListDto findEmoneyTotalUsageAmountEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Page<ResponseEmoneyLogListDto.LogDto> page = emoneyUsageHistoryRepository.findEmoneyTotalUsageAmountEachUser(emoneyUsageHistorySearchDto);

        ResponsePageDto responsePageDto = ResponsePageDto.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        return ResponseEmoneyLogListDto.builder()
                .list(page.getContent())
                .page(responsePageDto)
                .build();
    }

    @Override
    public ResponseEmoneyUsageHistoryLogListDto findEmoneyUsageHistoryEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Page<ResponseEmoneyUsageHistoryLogListDto.LogHistoryDto> page = emoneyUsageHistoryRepository.findEmoneyUsageHistoryEachUser(emoneyUsageHistorySearchDto);

        ResponsePageDto responsePageDto = ResponsePageDto.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        return ResponseEmoneyUsageHistoryLogListDto.builder()
                .list(page.getContent())
                .page(responsePageDto)
                .build();
    }

    @Override
    public ResponseEmoneyUserDetailListDto findEmoneyUserDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Page<ResponseEmoneyUserDetailListDto.EmoneyUserDetail> page = emoneyUsageHistoryRepository.findEmoneyUserDetail(emoneyUsageHistorySearchDto);

        ResponsePageDto responsePageDto = ResponsePageDto.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        return ResponseEmoneyUserDetailListDto.builder()
                .list(page.getContent())
                .page(responsePageDto)
                .build();
    }

    @Override
    public ResponseEmoneyUsageDeductionListDto findEmoneyUserUsageDeductionDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Page<ResponseEmoneyUsageDeductionListDto.UsageDeductionDetail> page = emoneyUsageHistoryRepository.findEmoneyUserUsageDeductionDetail(emoneyUsageHistorySearchDto);

        ResponsePageDto responsePageDto = ResponsePageDto.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        return ResponseEmoneyUsageDeductionListDto.builder()
                .list(page.getContent())
                .page(responsePageDto)
                .build();
    }
}
