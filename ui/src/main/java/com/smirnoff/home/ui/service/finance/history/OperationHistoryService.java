package com.smirnoff.home.ui.service.finance.history;

import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;

import java.util.Collection;

public interface OperationHistoryService {
    void delete(OperationHistoryModel operation);

    void update(OperationHistoryModel operation);

    Collection<OperationHistoryModel> getList();

    void create(OperationHistoryModel operation);
}
