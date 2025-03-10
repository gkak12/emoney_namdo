package com.emoney.service;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageDeductionListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUserDetailListDto;

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

    /**
     * 사용자별 적립금 사용내역 상세 조회
     * 
     * @param emoneyUsageHistorySearchDto
     * @return
     * 
     * 사용자 SEQ,
     * 사용자 적립금 사용횟수,
     * 사용자 최근 적립금 사용시간,
     * 사용자 평균 적립금,
     * 사용자 총 적립금
     */
    ResponseEmoneyUserDetailListDto findEmoneyUserDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);

    /**
     * 사용자별 적립금 사용 및 차감 횟수 조회
     * 
     * @param emoneyUsageHistorySearchDto
     * @return
     * 
     * 적립금 사용 횟수,
     * 적립금 차감 횟수
     */
    ResponseEmoneyUsageDeductionListDto findEmoneyUserUsageDeductionDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
