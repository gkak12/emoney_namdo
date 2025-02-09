package com.emoney.repository;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogVo;

import java.util.List;

public interface EmoneyUsageHistoryRepositoryDsl {

    List<EmoneyLogVo> findEmoneyTotalUsageAmountEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
