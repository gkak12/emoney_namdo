package com.emoney.api;

import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogVo;
import com.emoney.service.EmoneyUsageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emoney-usage-history")
@RequiredArgsConstructor
public class EmoneyUsageHistoryController {

    private final EmoneyUsageHistoryService emoneyUsageHistoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/total-usage-amount-each-user")
    public ResponseEntity<List<EmoneyLogVo>> findEmoneyTotalUsageAmountEachUser(@ParameterObject EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto){
        return ResponseEntity.ok(emoneyUsageHistoryService.findEmoneyTotalUsageAmountEachUser(emoneyUsageHistorySearchDto));
    }
}
