package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmoneyUsageHistoryLogListVo {

    private List<EmoneyUsageHistoryLogVo> list;
    private PageVo page;
}
