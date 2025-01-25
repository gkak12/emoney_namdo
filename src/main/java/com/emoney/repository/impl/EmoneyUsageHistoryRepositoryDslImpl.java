package com.emoney.repository.impl;

import com.emoney.repository.EmoneyUsageHistoryRepositoryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmoneyUsageHistoryRepositoryDslImpl implements EmoneyUsageHistoryRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;
}
