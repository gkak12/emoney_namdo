package com.emoney.repository;

import com.emoney.domain.dto.EmoneyUsageDto;
import com.emoney.domain.entity.Emoney;

import java.util.List;

public interface EmoneyRepositoryDsl {
    List<Emoney> findAllUsableEmoneyList(EmoneyUsageDto emoneyUsageDto);
}
