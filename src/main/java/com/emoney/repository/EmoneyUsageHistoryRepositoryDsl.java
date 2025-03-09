package com.emoney.repository;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogDto;
import com.emoney.domain.dto.response.ResponseEmoneyUserDetailDto;
import org.springframework.data.domain.Page;

public interface EmoneyUsageHistoryRepositoryDsl {

    Page<ResponseEmoneyLogDto> findEmoneyTotalUsageAmountEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    Page<ResponseEmoneyUsageHistoryLogDto> findEmoneyUsageHistoryEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
    Page<ResponseEmoneyUserDetailDto> findEmoneyUserDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto);
}
