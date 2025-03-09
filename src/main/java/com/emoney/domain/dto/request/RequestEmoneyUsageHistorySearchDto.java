package com.emoney.domain.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequestEmoneyUsageHistorySearchDto extends RequestPageDto {

    private Long userSeq;
    private LocalDate searchStartDate;
    private LocalDate searchEndDate;
    private Long usageTypeSeq;
}
