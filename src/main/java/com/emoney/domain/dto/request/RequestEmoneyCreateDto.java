package com.emoney.domain.dto.request;

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
public class RequestEmoneyCreateDto {

    @NotNull
    private Long userSeq;

    @NotNull
    private Long orderSeq;

    @NotNull
    private Long typeSeq;

    @NotNull
    private Long amount;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationDate;

    @NotNull
    private Boolean isApproved;

    @NotNull
    private Boolean isExpired;

    private String content;
}
