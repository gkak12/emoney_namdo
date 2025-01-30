package com.emoney.api;

import com.emoney.comm.annotation.ValidEmoneySeq;
import com.emoney.domain.dto.*;
import com.emoney.domain.vo.EmoneyDetailVo;
import com.emoney.domain.vo.EmoneyListVo;
import com.emoney.domain.vo.EmoneyVo;
import com.emoney.service.EmoneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<EmoneyVo>> findAllEmoneys() {
        return ResponseEntity.ok(emoneyService.findAllEmoneys());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public ResponseEntity<EmoneyListVo> findPageEmoneys(@RequestBody EmoneySearchDto emoneySearchDto) {
        return ResponseEntity.ok(emoneyService.findPageEmoneys(emoneySearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail/{emoneySeq}")
    public ResponseEntity<EmoneyDetailVo> findEmoneyDetail(@PathVariable @Valid @ValidEmoneySeq Long emoneySeq) {
        return ResponseEntity.ok(emoneyService.findEmoneyDetail(emoneySeq));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Void> createEmoney(@RequestBody @Valid EmoneyCreateDto emoneyCreateDto) {
        emoneyService.createEmoney(emoneyCreateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping({"/usage", "/deduct"})
    public ResponseEntity<Void> deductEmoney(@RequestBody @Valid EmoneyDeductDto emoneyDeductDto) {
        emoneyService.deductEmoney(emoneyDeductDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/usage-cancel")
    public ResponseEntity<Void> useCancelEmoney(@RequestBody @Valid EmoneyCancelDto emoneyCancelDto) {
        emoneyService.useCancelEmoney(emoneyCancelDto);
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
    public ResponseEntity<Void> extendEmoney(@RequestBody @Valid EmoneyExtendDto emoneyExtendDto) {
        emoneyService.extendEmoney(emoneyExtendDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/expire/{emoneySeq}")
    public ResponseEntity<Void> expireEmoney(@PathVariable @Valid @ValidEmoneySeq Long emoneySeq) {
        emoneyService.expireEmoney(emoneySeq);
        return ResponseEntity.ok().build();
    }
}
