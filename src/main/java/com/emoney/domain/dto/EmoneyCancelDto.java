package com.emoney.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmoneyCancelDto {

    @NotNull
    @Min(1)
    private Long userSeq;

    @NotNull
    @Min(1)
    private Long orderSeq;

    private String content;
}
