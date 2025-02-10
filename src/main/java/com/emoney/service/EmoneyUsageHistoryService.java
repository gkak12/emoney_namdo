package com.emoney.service;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogListVo;
import com.emoney.domain.vo.EmoneyUsageHistoryLogListVo;

public interface EmoneyUsageHistoryService {

    EmoneyLogListVo findEmoneyTotalUsageAmountEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    EmoneyUsageHistoryLogListVo findEmoneyUsageHistoryEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
