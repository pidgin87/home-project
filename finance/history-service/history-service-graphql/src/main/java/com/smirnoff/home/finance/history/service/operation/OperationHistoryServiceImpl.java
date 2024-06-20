package com.smirnoff.home.finance.history.service.operation;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.OperationHistoryRepository;
import com.smirnoff.home.finance.history.service.balance.BalanceFundService;
import com.smirnoff.home.finance.history.service.balance.BalanceProductService;
import com.smirnoff.home.finance.history.service.operation.lc.OperationHistoryLifecycle;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationHistoryServiceImpl implements OperationHistoryService {
    private final BalanceProductService balanceProductService;
    private final BalanceFundService balanceFundService;
    private final OperationHistoryRepository operationHistoryRepository;
    private final OperationHistoryLifecycle operationHistoryLifecycle;
    private final SessionClientService sessionClientService;

    @Override
    @Transactional
    public void save(OperationHistoryEntity operation) {
        operationHistoryLifecycle.create(operation);
        operationHistoryRepository.save(operation);

        balanceProductService.createByOperation(operation);
        balanceFundService.createByOperation(operation);
    }

    @Override
    public List<OperationHistoryEntity> getAll() {
        String companyId = sessionClientService.getCompanyId();
        return operationHistoryRepository.findByCompanyIdOrderByCreatedDateDesc(companyId);
    }
}
