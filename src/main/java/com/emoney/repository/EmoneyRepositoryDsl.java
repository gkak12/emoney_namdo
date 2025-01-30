package com.emoney.repository;

import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyDeductDto;
import com.emoney.domain.dto.EmoneySearchDto;
import com.emoney.domain.entity.Emoney;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface EmoneyRepositoryDsl {

    Page<Emoney> findEmoneyPaging(EmoneySearchDto searchDto);
    List<Emoney> findAllUsableEmoneyList(EmoneyDeductDto emoneyDeductDto);
    Map<String, Object> findCancellationEmoney(EmoneyCancelDto emoneyCancelDto);
}
