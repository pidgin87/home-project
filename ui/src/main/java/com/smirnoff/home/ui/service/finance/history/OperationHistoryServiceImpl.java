package com.smirnoff.home.ui.service.finance.history;

import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class OperationHistoryServiceImpl implements OperationHistoryService {
    @Override
    public void delete(OperationHistoryModel operation) {

    }

    @Override
    public void update(OperationHistoryModel operation) {

    }

    @Override
    public Collection<OperationHistoryModel> getList() {
        return Collections.emptyList();
    }

    @Override
    public void create(OperationHistoryModel operation) {

    }
}
