package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmoneyListVo {

    private List<EmoneyVo> list;
    private PageVo page;
}
