package com.smirnoff.home.finance.history.service.operation;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.OperationHistoryRepository;
import com.smirnoff.home.finance.history.service.operation.lc.OperationHistoryLifecycle;
import com.smirnoff.home.finance.history.service.service.ProductService;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationHistoryServiceImpl implements OperationHistoryService {
    private final OperationHistoryRepository operationHistoryRepository;
    private final OperationHistoryLifecycle operationHistoryLifecycle;
    private final SessionClientService sessionClientService;

    @Override
    public void save(OperationHistoryEntity operation) {
        operationHistoryLifecycle.create(operation);
        operationHistoryRepository.save(operation);
    }

    @Override
    public List<OperationHistoryEntity> getAll() {
        String companyId = sessionClientService.getCompanyId();
        return operationHistoryRepository.findByCompanyIdOrderByCreatedDateAsc(companyId);
    }
}
