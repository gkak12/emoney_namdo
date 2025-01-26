package com.emoney.repository;

import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyDeductDto;
import com.emoney.domain.entity.Emoney;

import java.util.List;
import java.util.Map;

public interface EmoneyRepositoryDsl {

    List<Emoney> findAllUsableEmoneyList(EmoneyDeductDto emoneyDeductDto);
    Map<String, Object> findCancellationEmoney(EmoneyCancelDto emoneyCancelDto);
}
