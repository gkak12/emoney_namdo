package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmoneyUsageHistoryVo {

    private Long emoneyUsageHistorySeq;
    private Long usageTypeSeq;
    private Long usageAmount;
    private String content;
    private String creationDateTime;
}
