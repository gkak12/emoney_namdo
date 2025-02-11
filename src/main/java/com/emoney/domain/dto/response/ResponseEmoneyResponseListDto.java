package com.emoney.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyResponseListDto {

    private List<ResponseEmoneyDto> list;
    private ResponsePageDto page;
}
