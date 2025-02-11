package com.emoney.api;

import com.emoney.comm.annotation.ValidEmoneySeq;
import com.emoney.domain.dto.request.*;
import com.emoney.domain.dto.response.ResponseEmoneyDto;
import com.emoney.domain.dto.response.ResponseEmoneyResponseDetailDto;
import com.emoney.domain.dto.response.ResponseEmoneyResponseListDto;
import com.emoney.service.EmoneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emoney")
@RequiredArgsConstructor
public class EmoneyController {

    private final EmoneyService emoneyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ResponseEmoneyDto>> findAllEmoneys() {
        return ResponseEntity.ok(emoneyService.findAllEmoneys());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public ResponseEntity<ResponseEmoneyResponseListDto> findPageEmoneys(@ParameterObject RequestEmoneySearchDto emoneyRequestSearchDto) {
        return ResponseEntity.ok(emoneyService.findPageEmoneys(emoneyRequestSearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail/{emoneySeq}")
    public ResponseEntity<ResponseEmoneyResponseDetailDto> findEmoneyDetail(@PathVariable @Valid @ValidEmoneySeq Long emoneySeq) {
        return ResponseEntity.ok(emoneyService.findEmoneyDetail(emoneySeq));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Void> createEmoney(@RequestBody @Valid RequestEmoneyCreateDto requestEmoneyCreateDto) {
        emoneyService.createEmoney(requestEmoneyCreateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping({"/usage", "/deduct"})
    public ResponseEntity<Void> deductEmoney(@RequestBody @Valid RequestEmoneyDeductDto requestEmoneyDeductDto) {
        emoneyService.deductEmoney(requestEmoneyDeductDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/usage-cancel")
    public ResponseEntity<Void> useCancelEmoney(@RequestBody @Valid RequestEmoneyCancelDto requestEmoneyCancelDto) {
        emoneyService.useCancelEmoney(requestEmoneyCancelDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/approve/{emoneySeq}")
    public ResponseEntity<Void> approveEmoney(@PathVariable @Valid @ValidEmoneySeq Long emoneySeq) {
        emoneyService.approveEmoney(emoneySeq);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/reject/{emoneySeq}")
    public ResponseEntity<Void> rejectEmoney(@PathVariable @Valid @ValidEmoneySeq Long emoneySeq) {
        emoneyService.rejectEmoney(emoneySeq);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/extend")
    public ResponseEntity<Void> extendEmoney(@RequestBody @Valid RequestEmoneyExtendDto requestEmoneyExtendDto) {
        emoneyService.extendEmoney(requestEmoneyExtendDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/expire/{emoneySeq}")
    public ResponseEntity<Void> expireEmoney(@PathVariable @Valid @ValidEmoneySeq Long emoneySeq) {
        emoneyService.expireEmoney(emoneySeq);
        return ResponseEntity.ok().build();
    }
}
