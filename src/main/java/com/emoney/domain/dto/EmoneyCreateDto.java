package com.emoney.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmoneyCreateDto {

    private Long userSeq;
    private Long orderSeq;
    private Long typeSeq;
    private Long amount;
    private Long usedAmonut;
    private Long remainAmount;
    private LocalDateTime expirationDate;
    private Boolean isApproved;
    private String content;
}
