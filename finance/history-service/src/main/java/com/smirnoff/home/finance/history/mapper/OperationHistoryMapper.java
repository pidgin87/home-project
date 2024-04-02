package com.smirnoff.home.finance.history.mapper;

import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationHistoryMapper {
    OperationHistoryEntity map(OperationHistoryDto fund);
}
