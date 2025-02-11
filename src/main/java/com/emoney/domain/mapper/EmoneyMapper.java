package com.emoney.domain.mapper;

import com.emoney.comm.util.DateTimeUtil;
import com.emoney.domain.dto.request.RequestEmoneyCreateDto;
import com.emoney.domain.dto.response.ResponseEmoneyDto;
import com.emoney.domain.entity.Emoney;
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

    @Mapping(source = "expirationDateTime", target = "expirationDateTime", qualifiedByName = "setLocalDateTimeToString")
    @Mapping(source = "creationDateTime", target = "creationDateTime", qualifiedByName = "setLocalDateTimeToString")
    ResponseEmoneyDto toResponseDto(Emoney emoney);

    @Named("setLocalDateTimeToString")
    default String setLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    @Mapping(target = "usageAmount", ignore = true)
    @Mapping(target = "remainAmount", ignore = true)
    @Mapping(target = "creationDateTime", ignore = true)
    Emoney toCreateEntity(RequestEmoneyCreateDto requestEmoneyCreateDto);

    @AfterMapping
    default void setAdditionalFields(RequestEmoneyCreateDto requestEmoneyCreateDto, @MappingTarget Emoney emoney) {
        emoney.setUsageAmount(0L);
        emoney.setRemainAmount(requestEmoneyCreateDto.getAmount());
        emoney.setCreationDateTime(DateTimeUtil.getLocalDateTime());
    }
}
