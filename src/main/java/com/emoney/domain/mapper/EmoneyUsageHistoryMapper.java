package com.emoney.domain.mapper;

import com.emoney.domain.dto.response.ResponseEmoneyUsageHistoryDto;
import com.emoney.domain.entity.EmoneyUsageHistory;
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
    ResponseEmoneyUsageHistoryDto toResponseDto(EmoneyUsageHistory emoneyUsageHistory);
}
