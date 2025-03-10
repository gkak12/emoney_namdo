package com.emoney.api;

import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageDeductionListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUserDetailListDto;
import com.emoney.service.EmoneyUsageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emoney-usage-history")
@RequiredArgsConstructor
public class EmoneyUsageHistoryController {

    private final EmoneyUsageHistoryService emoneyUsageHistoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/total-usage-amount-each-user")
    public ResponseEntity<ResponseEmoneyLogListDto> findEmoneyTotalUsageAmountEachUser(@ParameterObject RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyTotalUsageAmountEachUser(emoneyUsageHistorySearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usage-history-each-user")
    public ResponseEntity<ResponseEmoneyUsageHistoryLogListDto> findEmoneyUsageHistoryEachUser(@ParameterObject RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyUsageHistoryEachUser(emoneyUsageHistorySearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user-detail")
    public ResponseEntity<ResponseEmoneyUserDetailListDto> findEmoneyUserDetail(@ParameterObject RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyUserDetail(emoneyUsageHistorySearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usage-deduction")
    public ResponseEntity<ResponseEmoneyUsageDeductionListDto> findEmoneyUserUsageDeductionDetail(@ParameterObject RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyUserUsageDeductionDetail(emoneyUsageHistorySearchDto));
    }
}
