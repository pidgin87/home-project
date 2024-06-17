package com.smirnoff.home.finance.history.service.operation.lc;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class OperationHistoryLifecycleImpl implements OperationHistoryLifecycle {

    private final SessionClientService sessionClientService;

    @Override
    public void create(OperationHistoryEntity operation) {
        operation.setCreatedDate(LocalDateTime.now());
        operation.setCompanyId(sessionClientService.getCompanyId());
    }
}
