package com.emoney.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyResponseDetailDto {

    private ResponseEmoneyDto responseEmoneyDto;
    private List<ResponseEmoneyUsageHistoryDto> responseEmoneyUsageHistoryDtoList;
}
