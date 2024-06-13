package com.smirnoff.home.finance.history.service.operation.lc;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;

public interface OperationHistoryLifecycle {
    void create(OperationHistoryEntity operation);
}
