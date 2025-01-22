package com.emoney.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmoneyVo {

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
