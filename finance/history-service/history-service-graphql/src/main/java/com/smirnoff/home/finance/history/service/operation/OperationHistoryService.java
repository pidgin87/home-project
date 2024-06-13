package com.smirnoff.home.finance.history.service.operation;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;

import java.util.List;

public interface OperationHistoryService {
    void save(OperationHistoryEntity entity);

    List<OperationHistoryEntity> getAll();
}
