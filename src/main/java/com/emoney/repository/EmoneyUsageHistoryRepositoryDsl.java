package com.emoney.repository;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageDeductionListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogDto;
import com.emoney.domain.dto.response.ResponseEmoneyUserDetailListDto;
import org.springframework.data.domain.Page;

public interface EmoneyUsageHistoryRepositoryDsl {

    Page<ResponseEmoneyLogListDto.LogDto> findEmoneyTotalUsageAmountEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    Page<ResponseEmoneyUsageHistoryLogDto> findEmoneyUsageHistoryEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    Page<ResponseEmoneyUserDetailListDto.EmoneyUserDetail> findEmoneyUserDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    Page<ResponseEmoneyUsageDeductionListDto.UsageDeductionDetail> findEmoneyUserUsageDeductionDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
