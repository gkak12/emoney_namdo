package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyLogListDto {

    private List<LogDto> list;
    private ResponsePageDto page;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogDto {

        private Long userSeq;
        private Long usageAmount;
    }
}
