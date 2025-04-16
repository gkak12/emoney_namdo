package com.emoney.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmoneyDetailDto {

    private ResponseEmoneyDto responseEmoneyDto;
    private List<ResponseEmoneyUsageHistoryDto> responseEmoneyUsageHistoryDtoList;
}
