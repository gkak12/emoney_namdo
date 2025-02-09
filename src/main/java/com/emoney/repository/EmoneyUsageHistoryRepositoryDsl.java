package com.emoney.repository;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogVo;
import org.springframework.data.domain.Page;

public interface EmoneyUsageHistoryRepositoryDsl {

    Page<EmoneyLogVo> findEmoneyTotalUsageAmountEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
