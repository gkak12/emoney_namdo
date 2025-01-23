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
    private Long amount;
}
