package com.emoney.repository;

import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyUsageDto;
import com.emoney.domain.entity.Emoney;

import java.util.List;
import java.util.Map;

public interface EmoneyRepositoryDsl {

    List<Emoney> findAllUsableEmoneyList(EmoneyUsageDto emoneyUsageDto);
    Map<String, Object> findCancellationEmoney(EmoneyCancelDto emoneyCancelDto);
}
