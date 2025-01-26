package com.emoney.repository.impl;

import com.emoney.domain.dto.EmoneyCancelDto;
import com.emoney.domain.dto.EmoneyDeductDto;
import com.emoney.domain.entity.Emoney;
import com.emoney.repository.EmoneyRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.emoney.domain.entity.QEmoney.emoney;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmoneyRepositoryDslImpl implements EmoneyRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Emoney> findAllUsableEmoneyList(EmoneyDeductDto emoneyDeductDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(emoney.userSeq.eq(emoneyDeductDto.getUserSeq()))
            .and(emoney.remainAmount.gt(0L))
            .and(emoney.expirationDate.goe(emoneyDeductDto.getSearchDateTime()))
            .and(emoney.isApproved.eq(true));

        return jpaQueryFactory
                .select(emoney)
                .from(emoney)
                .where(builder)
                .orderBy(emoney.expirationDate.asc())
                .fetch();
    }

    @Override
    public Map<String, Object> findCancellationEmoney(EmoneyCancelDto emoneyCancelDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(emoney.userSeq.eq(emoneyCancelDto.getUserSeq()));

        LocalDateTime expirationDate = jpaQueryFactory
                .select(emoney.expirationDate.max())
                .from(emoney)
                .where(builder)
                .fetchOne();

        builder
            .and(emoney.orderSeq.eq(emoneyCancelDto.getOrderSeq()));

        Long amount = jpaQueryFactory
                .select(emoney.amount)
                .from(emoney)
                .where(builder)
                .fetchOne();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("expirationDate", expirationDate);
        resultMap.put("amount", amount);

        return resultMap;
    }
}
