package com.smirnoff.home.finance.history.service.balance;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;

public interface BalanceFundService {
    void createByOperation(OperationHistoryEntity operation);
}
