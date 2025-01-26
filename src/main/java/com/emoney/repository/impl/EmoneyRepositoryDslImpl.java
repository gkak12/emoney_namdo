package com.emoney.repository.impl;

import com.emoney.domain.dto.EmoneyUsageDto;
import com.emoney.domain.entity.Emoney;
import com.emoney.repository.EmoneyRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.emoney.domain.entity.QEmoney.emoney;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmoneyRepositoryDslImpl implements EmoneyRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Emoney> findAllUsableEmoneyList(EmoneyUsageDto emoneyUsageDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(emoney.userSeq.eq(emoneyUsageDto.getUserSeq()))
            .and(emoney.remainAmount.gt(0L))
            .and(emoney.expirationDate.goe(emoneyUsageDto.getSearchDateTime()));

        return jpaQueryFactory
                .select(emoney)
                .from(emoney)
                .where(builder)
                .orderBy(emoney.expirationDate.asc())
                .fetch();
    }
}
