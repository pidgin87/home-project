package com.smirnoff.home.platform.backup.mapper;

import com.smirnoff.home.platform.backup.model.BackupEntityModel;
import com.smirnoff.home.platform.backup.model.debezium.DebeziumMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BackupEntityModelMapper {

    @Mapping(target = "fields", source = "payload.after")
    @Mapping(target = "meta.schema", source = "payload.source.schema")
    @Mapping(target = "meta.table", source = "payload.source.table")
    BackupEntityModel map(DebeziumMessage message);
}
