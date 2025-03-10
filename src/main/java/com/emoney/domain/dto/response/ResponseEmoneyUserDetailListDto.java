package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyUserDetailListDto {

    private List<EmoneyUserDetail> list;
    private ResponsePageDto page;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmoneyUserDetail {

        private Long userSeq;
        private Long usageCount;
        private String latestUsageDateTime;
        private Long averageAmount;
        private Long totalAmount;
    }
}
