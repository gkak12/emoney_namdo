package com.emoney.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmoneyDeductDto {

    @NotNull
    private Long userSeq;

    private Long orderSeq;

    @NotNull
    private Long typeSeq;

    @NotNull
    private Long usageTypeSeq;

    @NotNull
    private Long amount;

    private String content;

    private LocalDateTime searchDateTime;
}