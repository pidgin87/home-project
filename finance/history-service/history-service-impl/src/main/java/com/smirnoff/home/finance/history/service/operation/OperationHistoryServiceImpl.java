package com.smirnoff.home.finance.history.service.operation;

import com.smirnoff.home.finance.history.model.ProductDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.OperationHistoryRepository;
import com.smirnoff.home.finance.history.service.operation.lc.OperationHistoryLifecycle;
import com.smirnoff.home.finance.history.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationHistoryServiceImpl implements OperationHistoryService {
    private final ProductService productService;
    private final OperationHistoryRepository operationHistoryRepository;
    private final OperationHistoryLifecycle operationHistoryLifecycle;

    @Override
    public void save(OperationHistoryEntity operation) {
        operationHistoryLifecycle.create(operation);

        operationHistoryRepository.save(operation);
    }

    @Override
    public List<OperationHistoryEntity> getAll() {
        return operationHistoryRepository.findAll(
                Sort.by(Sort.Direction.DESC, "createdDate")
        );
    }
}
