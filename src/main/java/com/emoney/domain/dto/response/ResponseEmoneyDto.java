package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmoneyDto {

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
