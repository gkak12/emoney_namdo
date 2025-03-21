package com.emoney.repository.impl;

import com.emoney.comm.enums.EmoneySearchEnums;
import com.emoney.comm.util.ConditionBuilderUtil;
import com.emoney.domain.dto.request.RequestEmoneyCancelDto;
import com.emoney.domain.dto.request.RequestEmoneyDeductDto;
import com.emoney.domain.dto.request.RequestEmoneySearchDto;
import com.emoney.domain.entity.Emoney;
import com.emoney.repository.EmoneyRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.emoney.domain.entity.QEmoney.emoney;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmoneyRepositoryDslImpl implements EmoneyRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Emoney> findEmoneyPaging(RequestEmoneySearchDto searchDto) {
        Pageable pageable = PageRequest.of(
                searchDto.getPageNumber(),
                searchDto.getPageSize()
        );

        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilderUtil.buildEquals(emoney.emoneySeq, searchDto.getEmoneySeq()))
            .and(ConditionBuilderUtil.buildEquals(emoney.userSeq, searchDto.getUserSeq()))
            .and(ConditionBuilderUtil.buildEquals(emoney.orderSeq, searchDto.getOrderSeq()))
            .and(ConditionBuilderUtil.buildEquals(emoney.typeSeq, searchDto.getTypeSeq()))
            .and(ConditionBuilderUtil.buildStringLike(emoney.content, searchDto.getContent()));

        String amountType = searchDto.getSearchAmountType();
        Long startAmount = searchDto.getSearchStartAmountVal();
        Long endAmount = searchDto.getSearchEndAmountVal();

        if(EmoneySearchEnums.AMOUNT.getVal().equals(amountType)){
            builder.and(ConditionBuilderUtil.buildAmountBetween(emoney.amount, startAmount, endAmount));
        } else if(EmoneySearchEnums.USAGE_AMOUNT.getVal().equals(amountType)){
            builder.and(ConditionBuilderUtil.buildAmountBetween(emoney.usageAmount, startAmount, endAmount));
        } else if(EmoneySearchEnums.REMAIN_AMOUNT.getVal().equals(amountType)){
            builder.and(ConditionBuilderUtil.buildAmountBetween(emoney.remainAmount, startAmount, endAmount));
        }

        String dateType = searchDto.getSearchDateType();
        LocalDate startDate = searchDto.getSearchStartDate();
        LocalDate endDate = searchDto.getSearchEndDate();

        if(EmoneySearchEnums.EXPIRATION_DATE.getVal().equals(dateType)){
            builder.and(ConditionBuilderUtil.buildDateBetween(emoney.expirationDateTime, startDate, endDate));
        } else if(EmoneySearchEnums.CREATION_DATE.getVal().equals(dateType)){
            builder.and(ConditionBuilderUtil.buildDateBetween(emoney.creationDateTime, startDate, endDate));
        }

        String statusType = searchDto.getSearchStatusType();
        Boolean status = searchDto.getSearchStatusVal();

        if(EmoneySearchEnums.IS_APPROVED.getVal().equals(statusType)){
            builder.and(ConditionBuilderUtil.buildEquals(emoney.isApproved, status));
        } else if(EmoneySearchEnums.IS_EXPIRED.getVal().equals(statusType)){
            builder.and(ConditionBuilderUtil.buildEquals(emoney.isExpired, status));
        }

        List<Emoney> list = jpaQueryFactory
                .select(emoney)
                .from(emoney)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = Optional.ofNullable(
                jpaQueryFactory
                .select(emoney.count().as("count"))
                .from(emoney)
                .where(builder)
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public List<Emoney> findAllUsableEmoneyList(RequestEmoneyDeductDto requestEmoneyDeductDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(emoney.userSeq.eq(requestEmoneyDeductDto.getUserSeq()))
            .and(emoney.remainAmount.gt(0L))
            .and(emoney.expirationDateTime.goe(requestEmoneyDeductDto.getSearchDateTime()))
            .and(emoney.isApproved.eq(true))
            .and(emoney.isExpired.eq(false));

        return jpaQueryFactory
                .select(emoney)
                .from(emoney)
                .where(builder)
                .orderBy(emoney.expirationDateTime.asc())
                .fetch();
    }

    @Override
    public Map<String, Object> findCancellationEmoney(RequestEmoneyCancelDto requestEmoneyCancelDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(emoney.userSeq.eq(requestEmoneyCancelDto.getUserSeq()));

        LocalDateTime expirationDate = jpaQueryFactory
                .select(emoney.expirationDateTime.max())
                .from(emoney)
                .where(builder)
                .fetchOne();

        builder
            .and(emoney.orderSeq.eq(requestEmoneyCancelDto.getOrderSeq()));

        Long amount = jpaQueryFactory
                .select(emoney.amount)
                .from(emoney)
                .where(builder)
                .fetchOne();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(EmoneySearchEnums.EXPIRATION_DATE.getVal(), expirationDate);
        resultMap.put(EmoneySearchEnums.AMOUNT.getVal(), amount);

        return resultMap;
    }
}
