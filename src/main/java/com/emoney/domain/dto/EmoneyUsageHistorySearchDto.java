package com.emoney.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmoneyUsageHistorySearchDto extends PageDto {

    private Long userSeq;
    private LocalDate searchStartDate;
    private LocalDate searchEndDate;
}
