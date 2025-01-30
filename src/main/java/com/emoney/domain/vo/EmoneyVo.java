package com.emoney.domain.vo;

import lombok.Data;

@Data
public class EmoneyVo extends PageVo{

    private Long emoneySeq;
    private Long userSeq;
    private Long orderSeq;
    private Long typeSeq;
    private Long amount;
    private Long usageAmount;
    private Long remainAmount;
    private String expirationDate;
    private String creationDate;
    private Boolean isApproved;
    private Boolean isExpired;
    private String content;
}
