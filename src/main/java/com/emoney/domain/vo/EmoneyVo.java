package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmoneyVo{

    private Long emoneySeq;
    private Long userSeq;
    private Long orderSeq;
    private Long typeSeq;
    private Long amount;
    private Long usageAmount;
    private Long remainAmount;
    private String expirationDateTime;
    private String creationDateTime;
    private Boolean isApproved;
    private Boolean isExpired;
    private String content;
}
