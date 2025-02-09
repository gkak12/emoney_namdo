package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmoneyLogListVo {

    private List<EmoneyLogVo> list;
    private PageVo page;
}
