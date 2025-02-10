package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmoneyUsageHistoryLogVo {

    private Long emoneySeq;
    private Long userSeq;
    private Long orderSeq;
    private Long typeSeq;
    private Long emoneyUsageHistorySeq;
    private Long usageTypeSeq;
    private Long usageAmount;
    private String content;
    private LocalDateTime creationDateTime;
}
