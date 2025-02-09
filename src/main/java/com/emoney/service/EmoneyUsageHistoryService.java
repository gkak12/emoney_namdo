package com.emoney.service;

import com.emoney.domain.vo.EmoneyLogVo;

import java.util.List;

public interface EmoneyUsageHistoryService {

    List<EmoneyLogVo> findEmoneyTotalUsageAmountEachUser();
}
