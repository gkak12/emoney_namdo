package com.emoney.repository;

import com.emoney.domain.entity.EmoneyUsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmoneyUsageHistoryRepository extends JpaRepository<EmoneyUsageHistory, Long>, EmoneyUsageHistoryRepositoryDsl {
}
