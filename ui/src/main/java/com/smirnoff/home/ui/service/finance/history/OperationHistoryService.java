package com.smirnoff.home.ui.service.finance.history;

import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;

import java.util.Collection;

public interface OperationHistoryService {
    void delete(OperationHistoryDto operation);

    void update(OperationHistoryModel operation);

    Collection<OperationHistoryDto> getList();

    void create(OperationHistoryModel operation);
}
