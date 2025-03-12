package com.emoney.repository.impl;

import com.emoney.comm.enums.EmoneyTypeEnums;
import com.emoney.comm.util.ConditionBuilderUtil;
import com.emoney.domain.dto.request.RequestEmoneyUsageHistorySearchDto;
import com.emoney.domain.dto.response.ResponseEmoneyLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageDeductionListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryLogListDto;
import com.emoney.domain.dto.response.ResponseEmoneyUserDetailListDto;
import com.emoney.repository.EmoneyUsageHistoryRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ResponseEmoneyLogListDto.LogDto> findEmoneyTotalUsageAmountEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Pageable pageable = PageRequest.of(
            emoneyUsageHistorySearchDto.getPageNumber(),
            emoneyUsageHistorySearchDto.getPageSize()
        );

        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilderUtil.buildEquals(emoney.userSeq, emoneyUsageHistorySearchDto.getUserSeq()))
            .and(ConditionBuilderUtil.buildDateBetween(emoneyUsageHistory.creationDateTime,
                emoneyUsageHistorySearchDto.getSearchStartDate(),
                emoneyUsageHistorySearchDto.getSearchEndDate()));

        List<ResponseEmoneyLogListDto.LogDto> list = jpaQueryFactory
            .select(Projections.fields(
                ResponseEmoneyLogListDto.LogDto.class,
                emoney.userSeq,
                emoneyUsageHistory.usageAmount.sum().as("usageAmount")
            ))
            .from(emoneyUsageHistory)
            .leftJoin(emoney)
            .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
            .where(builder)
            .groupBy(emoney.userSeq)
            .orderBy(emoney.userSeq.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        int count = jpaQueryFactory
                .select(emoney.userSeq.count())
                .from(emoneyUsageHistory)
                .leftJoin(emoney)
                .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
                .where(builder)
                .groupBy(emoney.userSeq)
                .fetch()
                .size();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ResponseEmoneyUsageHistoryLogListDto.LogHistoryDto> findEmoneyUsageHistoryEachUser(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Pageable pageable = PageRequest.of(
            emoneyUsageHistorySearchDto.getPageNumber(),
            emoneyUsageHistorySearchDto.getPageSize()
        );

        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilderUtil.buildEquals(emoney.userSeq, emoneyUsageHistorySearchDto.getUserSeq()))
            .and(ConditionBuilderUtil.buildDateBetween(emoneyUsageHistory.creationDateTime,
                emoneyUsageHistorySearchDto.getSearchStartDate(),
                emoneyUsageHistorySearchDto.getSearchEndDate()));

        List<ResponseEmoneyUsageHistoryLogListDto.LogHistoryDto> list = jpaQueryFactory
            .select(Projections.fields(
                    ResponseEmoneyUsageHistoryLogListDto.LogHistoryDto.class,
                    emoney.emoneySeq,
                    emoney.userSeq,
                    emoney.orderSeq,
                    emoney.typeSeq,
                    emoneyUsageHistory.emoneyUsageHistorySeq,
                    emoneyUsageHistory.usageTypeSeq,
                    emoneyUsageHistory.usageAmount,
                    emoneyUsageHistory.content,
                    emoneyUsageHistory.creationDateTime
            ))
            .from(emoney)
            .leftJoin(emoneyUsageHistory)
            .on(emoney.emoneySeq.eq(emoneyUsageHistory.emoney.emoneySeq))
            .where(builder)
            .orderBy(emoney.userSeq.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        int count = jpaQueryFactory
                .select(emoney.count())
                .from(emoney)
                .leftJoin(emoneyUsageHistory)
                .on(emoney.emoneySeq.eq(emoneyUsageHistory.emoney.emoneySeq))
                .where(builder)
                .fetch()
                .size();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ResponseEmoneyUserDetailListDto.EmoneyUserDetail> findEmoneyUserDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Pageable pageable = PageRequest.of(
                emoneyUsageHistorySearchDto.getPageNumber(),
                emoneyUsageHistorySearchDto.getPageSize()
        );

        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilderUtil.buildDateBetween(emoneyUsageHistory.creationDateTime,
                emoneyUsageHistorySearchDto.getSearchStartDate(),
                emoneyUsageHistorySearchDto.getSearchEndDate()))
            .and(ConditionBuilderUtil.buildEquals(emoneyUsageHistory.usageTypeSeq, emoneyUsageHistorySearchDto.getUsageTypeSeq()))
            .and(emoneyUsageHistory.usageTypeSeq.eq(EmoneyTypeEnums.USAGE.getVal()));

        List<ResponseEmoneyUserDetailListDto.EmoneyUserDetail> list = jpaQueryFactory
                .select(Projections.fields(
                        ResponseEmoneyUserDetailListDto.EmoneyUserDetail.class,
                        emoney.userSeq,
                        emoney.userSeq.count().as("usageCount"),
                        Expressions.stringTemplate("TO_CHAR({0}, 'YYYY-MM-DD HH24:MI:SS')",     // Postgresql 문법 적용(TO_CHAR)
                            emoneyUsageHistory.creationDateTime.max()).as("latestUsageDateTime"),
                        Expressions.numberTemplate(Long.class, "ROUND({0}, 0)",               // 소수점 첫번째 자리에서 반올림
                            emoneyUsageHistory.usageAmount.avg()).as("averageAmount"),
                        emoneyUsageHistory.usageAmount.sum().as("totalAmount")
                ))
                .from(emoneyUsageHistory)
                .join(emoney)
                .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
                .where(builder)
                .groupBy(emoney.userSeq)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int count = jpaQueryFactory
                .select(emoney.userSeq.count())
                .from(emoneyUsageHistory)
                .join(emoney)
                .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
                .where(builder)
                .groupBy(emoney.userSeq)
                .fetch()
                .size();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ResponseEmoneyUsageDeductionListDto.UsageDeductionDetail> findEmoneyUserUsageDeductionDetail(RequestEmoneyUsageHistorySearchDto emoneyUsageHistorySearchDto) {
        Pageable pageable = PageRequest.of(
                emoneyUsageHistorySearchDto.getPageNumber(),
                emoneyUsageHistorySearchDto.getPageSize()
        );

        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilderUtil.buildDateBetween(emoneyUsageHistory.creationDateTime,
                emoneyUsageHistorySearchDto.getSearchStartDate(),
                emoneyUsageHistorySearchDto.getSearchEndDate()))
            .and(ConditionBuilderUtil.buildEquals(emoneyUsageHistory.usageTypeSeq, emoneyUsageHistorySearchDto.getUsageTypeSeq()));

        List<ResponseEmoneyUsageDeductionListDto.UsageDeductionDetail> list = jpaQueryFactory
            .select(Projections.fields(
                ResponseEmoneyUsageDeductionListDto.UsageDeductionDetail.class,
                emoney.userSeq,
                Expressions.numberTemplate(Long.class, "SUM(CASE WHEN {0} = 2 THEN 1 ELSE 0 END)", EmoneyTypeEnums.USAGE.getVal()).as("usageCount"),
                Expressions.numberTemplate(Long.class, "SUM(CASE WHEN {0} = 4 THEN 1 ELSE 0 END)", EmoneyTypeEnums.DEDUCTION.getVal()).as("deductionCount")
            ))
            .from(emoneyUsageHistory)
            .join(emoney)
            .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
            .where(builder)
            .groupBy(emoney.userSeq)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        int count = jpaQueryFactory
            .select(emoney.userSeq.count())
            .from(emoneyUsageHistory)
            .join(emoney)
            .on(emoneyUsageHistory.emoney.emoneySeq.eq(emoney.emoneySeq))
            .where(builder)
            .groupBy(emoney.userSeq)
            .fetch()
            .size();

        return new PageImpl<>(list, pageable, count);
    }
}
