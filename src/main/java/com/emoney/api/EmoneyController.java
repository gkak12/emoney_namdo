package com.emoney.api;

import com.emoney.domain.vo.EmoneyVo;
import com.emoney.service.EmoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emoney")
@RequiredArgsConstructor
public class EmoneyController {

    private final EmoneyService emoneyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<EmoneyVo>> getEmoney() {
        return ResponseEntity.ok(emoneyService.findAllEmoneys());
    }
}
