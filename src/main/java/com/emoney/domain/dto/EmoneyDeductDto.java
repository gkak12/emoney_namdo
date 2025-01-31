package com.emoney.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime searchDateTime;
}
