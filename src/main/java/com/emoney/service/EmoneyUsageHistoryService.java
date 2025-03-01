package com.emoney.service;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogListDto;

public interface EmoneyUsageHistoryService {

    /**
     * 사용자별 적립금 누적 사용금 페이징 조회
     * 
     * @param emoneyUsageHistorySearchDto
     * @return
     */
    ResponseEmoneyLogListDto findEmoneyTotalUsageAmountEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);

    /**
     * 사용자별 적립금 사용내역 페이징 조회
     * 
     * @param emoneyUsageHistorySearchDto
     * @return
     */
    ResponseEmoneyUsageHistoryLogListDto findEmoneyUsageHistoryEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
