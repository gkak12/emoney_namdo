package com.emoney.repository.impl;

import com.emoney.comm.util.ConditionBuilderUtil;
import com.emoney.domain.dto.EmoneyUsageHistorySearchDto;
import com.emoney.domain.vo.EmoneyLogVo;
import com.emoney.repository.EmoneyUsageHistoryRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.emoney.domain.entity.QEmoney.emoney;
import static com.emoney.domain.entity.QEmoneyUsageHistory.emoneyUsageHistory;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmoneyUsageHistoryRepositoryDslImpl implements EmoneyUsageHistoryRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<EmoneyLogVo> findEmoneyTotalUsageAmountEachUser(EmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilderUtil.buildEquals(emoney.userSeq, emoneyUsageHistorySearchDto.getUserSeq()))
            .and(ConditionBuilderUtil.buildDateBetween(emoneyUsageHistory.creationDateTime,
                    emoneyUsageHistorySearchDto.getSearchStartDate(),
                    emoneyUsageHistorySearchDto.getSearchEndDate()));

        return jpaQueryFactory
            .select(Projections.fields(
                EmoneyLogVo.class,
                emoney.userSeq,
                emoneyUsageHistory.usageAmount.sum().as("usageAmount")
            ))
            .from(emoneyUsageHistory)
            .leftJoin(emoney)
            .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
            .where(builder)
            .groupBy(emoney.userSeq)
            .fetch();
    }
}
