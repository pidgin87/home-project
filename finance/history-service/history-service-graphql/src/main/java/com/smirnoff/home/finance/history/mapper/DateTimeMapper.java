package com.smirnoff.home.finance.history.mapper;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface DateTimeMapper {
    default OffsetDateTime map(LocalDateTime value) {
        ZoneId zoneId = ZoneId.systemDefault();
        return value.atZone(zoneId).toOffsetDateTime();
    }
}
