package com.emoney.domain.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequestEmoneySearchDto extends RequestPageDto {

    private Long emoneySeq;
    private Long userSeq;
    private Long orderSeq;
    private Long typeSeq;
    private String content;

    // 적립금 금액 검색 타입(amount, usageAmount, remainAmount)
    private String searchAmountType;
    private Long searchStartAmountVal;
    private Long searchEndAmountVal;

    // 적립금 시간 검색 타입(expirationDate, creationDate)
    private String searchDateType;
    private LocalDate searchStartDate;
    private LocalDate searchEndDate;

    // 적립금 여부 검색 타입(isApproved, isExpired)
    private String searchStatusType;
    private Boolean searchStatusVal;
}



