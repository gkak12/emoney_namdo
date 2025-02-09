package com.emoney.service.impl;

import com.emoney.domain.vo.EmoneyLogVo;
import com.emoney.repository.EmoneyUsageHistoryRepository;
import com.emoney.service.EmoneyUsageHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmoneyUsageHistoryServiceImpl implements EmoneyUsageHistoryService {

    private final EmoneyUsageHistoryRepository emoneyUsageHistoryRepository;

    @Override
    public List<EmoneyLogVo> findEmoneyTotalUsageAmountEachUser() {
        return emoneyUsageHistoryRepository.findEmoneyTotalUsageAmountEachUser();
    }
}
