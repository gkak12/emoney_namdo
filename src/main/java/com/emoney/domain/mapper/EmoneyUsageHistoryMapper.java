package com.emoney.domain.mapper;

import com.emoney.domain.entity.EmoneyUsageHistory;
import com.emoney.domain.vo.EmoneyUsageHistoryVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface EmoneyUsageHistoryMapper {

    @Mapping(source = "creationDateTime", target = "creationDateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EmoneyUsageHistoryVo toVo(EmoneyUsageHistory emoneyUsageHistory);
}
