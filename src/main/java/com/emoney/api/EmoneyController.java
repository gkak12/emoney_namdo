package com.emoney.api;

import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.dto.EmoneyDeductDto;
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
    public ResponseEntity<List<EmoneyVo>> getEmoney() {
        return ResponseEntity.ok(emoneyService.findAllEmoneys());
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
}
