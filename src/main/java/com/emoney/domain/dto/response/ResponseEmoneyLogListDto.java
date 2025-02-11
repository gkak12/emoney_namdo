package com.emoney.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseEmoneyLogListDto {

    private List<ResponseEmoneyLogDto> list;
    private ResponsePageDto page;
}
