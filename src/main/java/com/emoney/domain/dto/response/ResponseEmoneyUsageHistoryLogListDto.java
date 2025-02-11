package com.emoney.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyUsageHistoryLogListDto {

    private List<ResponseEmoneyUsageHistoryLogDto> list;
    private ResponsePageDto page;
}
