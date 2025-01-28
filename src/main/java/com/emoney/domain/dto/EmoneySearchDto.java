package com.emoney.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmoneySearchDto extends PageDto {

    private Long emoneySeq;
    private Long userSeq;
    private Long orderSeq;
    private Long typeSeq;
    private String content;

    // 적립금 금액 검색 타입(amount, usageAmount, remainAmount)
    private String searchAmountType;
    private Long searchAmountVal;

    // 적립금 시간 검색 타입(expirationDate, creationDate)
    private String searchDateType;
    private String searchStartDate;
    private String searchEndDate;

    // 적립금 여부 검색 타입(isApproved, isExpired)
    private String searchStatusType;
    private Boolean searchStatusVal;
}
