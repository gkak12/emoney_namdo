package com.emoney.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEmoneyUsageHistoryDto {

    private Long emoneyUsageHistorySeq;
    private Long usageTypeSeq;
    private Long usageAmount;
    private String content;
    private String creationDateTime;
}
