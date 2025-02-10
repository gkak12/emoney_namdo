package com.emoney.api;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogListVo;
import com.emoney.domain.vo.EmoneyUsageHistoryLogListVo;
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
    public ResponseEntity<EmoneyLogListVo> findEmoneyTotalUsageAmountEachUser(@ParameterObject EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyTotalUsageAmountEachUser(emoneyUsageHistorySearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usage-history-each-user")
    public ResponseEntity<EmoneyUsageHistoryLogListVo> findEmoneyUsageHistoryEachUser(@ParameterObject EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyUsageHistoryEachUser(emoneyUsageHistorySearchDto));
    }
}
