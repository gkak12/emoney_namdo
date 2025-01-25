package com.emoney.domain.mapper;

import com.emoney.comm.DateTimeUtil;
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

    @Mapping(target = "usageAmount", ignore = true)
    @Mapping(target = "remainAmount", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Emoney toCreateEntity(EmoneyCreateDto emoneyCreateDto);

    @AfterMapping
    default void setAdditionalFields(EmoneyCreateDto emoneyCreateDto, @MappingTarget Emoney emoney) {
        emoney.setUsageAmount(0L);
        emoney.setRemainAmount(emoneyCreateDto.getAmount());
        emoney.setCreationDate(DateTimeUtil.getLocalDateTime());
    }
}
