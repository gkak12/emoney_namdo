package com.emoney.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyUserDetailListDto {

    private List<ResponseEmoneyUserDetailDto> list;
    private ResponsePageDto page;
}
