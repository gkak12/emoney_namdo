package com.emoney.service;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogListVo;
import com.emoney.domain.vo.EmoneyLogVo;

import java.util.List;

public interface EmoneyUsageHistoryService {

    EmoneyLogListVo findEmoneyTotalUsageAmountEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
