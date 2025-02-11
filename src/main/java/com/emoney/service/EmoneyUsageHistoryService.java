package com.emoney.service;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogListDto;

public interface EmoneyUsageHistoryService {

    ResponseEmoneyLogListDto findEmoneyTotalUsageAmountEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    ResponseEmoneyUsageHistoryLogListDto findEmoneyUsageHistoryEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
