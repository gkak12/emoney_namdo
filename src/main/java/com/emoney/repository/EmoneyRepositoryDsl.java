package com.emoney.repository;

import com.emoney.domain.entity.Emoney;

import java.util.List;

public interface EmoneyRepositoryDsl {
    List<Emoney> findAllUsableEmoneyList(Long userSeq);
}
