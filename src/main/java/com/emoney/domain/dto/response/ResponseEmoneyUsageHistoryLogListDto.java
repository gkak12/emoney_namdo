package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmoneyUsageHistoryLogListDto {

    private List<LogHistoryDto> list;
    private ResponsePageDto page;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogHistoryDto{

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
}
