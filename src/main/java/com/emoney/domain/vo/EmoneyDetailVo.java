package com.emoney.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmoneyDetailVo {

    private EmoneyVo emoneyVo;
    private List<EmoneyUsageHistoryVo> emoneyUsageHistoryVoList;
}
