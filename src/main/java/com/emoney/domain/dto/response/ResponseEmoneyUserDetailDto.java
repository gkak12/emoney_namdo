package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmoneyUserDetailDto {

    private Long userSeq;
    private Long usageCount;
    private String latestUsageDateTime;
    private Double averageAmount;
    private Long totalAmount;
}
