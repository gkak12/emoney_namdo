package com.emoney.domain.mapper;

import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.entity.Emoney;
import com.emoney.domain.vo.EmoneyVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface EmoneyMapper {

    EmoneyVo toVo(Emoney emoney);
    Emoney toCreateEntity(EmoneyCreateDto emoneyCreateDto);
}
