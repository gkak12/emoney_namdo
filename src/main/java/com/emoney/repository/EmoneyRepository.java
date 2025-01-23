package com.emoney.repository;

import com.emoney.domain.entity.Emoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmoneyRepository extends JpaRepository<Emoney, Long>, EmoneyRepositoryDsl {
}
