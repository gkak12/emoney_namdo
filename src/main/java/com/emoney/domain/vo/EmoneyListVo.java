package com.emoney.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class EmoneyListVo {

    private List<EmoneyVo> list;
    private PageVo page;
}
