package com.emoney.repository;

import com.emoney.domain.dto.request.RequestEmoneyCancelDto;
import com.emoney.domain.dto.request.RequestEmoneyDeductDto;
import com.emoney.domain.dto.request.RequestEmoneySearchDto;
import com.emoney.domain.entity.Emoney;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface EmoneyRepositoryDsl {

    Page<Emoney> findEmoneyPaging(RequestEmoneySearchDto searchDto);
    List<Emoney> findAllUsableEmoneyList(RequestEmoneyDeductDto requestEmoneyDeductDto);
    Map<String, Object> findCancellationEmoney(RequestEmoneyCancelDto requestEmoneyCancelDto);
}
