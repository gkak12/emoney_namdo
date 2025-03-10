package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyUsageDeductionListDto {

    private List<ResponseEmoneyUsageDeductionListDto.UsageDeductionDetail> list;
    private ResponsePageDto page;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsageDeductionDetail {

        private Long userSeq;
        private Long usageCount;
        private Long deductionCount;
    }
}
