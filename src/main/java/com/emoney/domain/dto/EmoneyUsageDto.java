package com.emoney.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmoneyUsageDto {

    @NotNull
    private Long userSeq;

    @NotNull
    private Long orderSeq;

    @NotNull
    private Long typeSeq;

    @NotNull
    private Long usageTypeSeq;

    @NotNull
    private Long amount;

    private String content;
}
