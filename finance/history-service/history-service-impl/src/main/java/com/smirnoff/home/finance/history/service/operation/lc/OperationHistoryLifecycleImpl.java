package com.smirnoff.home.finance.history.service.operation.lc;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class OperationHistoryLifecycleImpl implements OperationHistoryLifecycle {
    @Override
    public void create(OperationHistoryEntity operation) {
        operation.setCreatedDate(OffsetDateTime.now());
    }
}
