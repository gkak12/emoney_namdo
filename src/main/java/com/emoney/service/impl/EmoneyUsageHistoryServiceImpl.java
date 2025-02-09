package com.emoney.service.impl;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogListVo;
import com.emoney.domain.vo.EmoneyLogVo;
import com.emoney.domain.vo.PageVo;
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
    public EmoneyLogListVo findEmoneyTotalUsageAmountEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Page<EmoneyLogVo> page = emoneyUsageHistoryRepository.findEmoneyTotalUsageAmountEachUser(emoneyUsageHistorySearchDto);

        PageVo pageVo = PageVo.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        return EmoneyLogListVo.builder()
                .list(page.getContent())
                .page(pageVo)
                .build();
    }
}
