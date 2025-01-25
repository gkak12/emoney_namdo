package com.emoney.domain.mapper;

import com.emoney.comm.DateTimeUtil;
import com.emoney.domain.dto.EmoneyCreateDto;
import com.emoney.domain.entity.Emoney;
import com.emoney.domain.vo.EmoneyVo;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface EmoneyMapper {

    @Mapping(source = "expirationDate", target = "expirationDate", qualifiedByName = "setLocalDateTimeToString")
    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "setLocalDateTimeToString")
    EmoneyVo toVo(Emoney emoney);

    @Named("setLocalDateTimeToString")
    default String setLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

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
