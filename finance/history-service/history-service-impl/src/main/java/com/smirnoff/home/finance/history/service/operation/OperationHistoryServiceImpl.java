package com.smirnoff.home.finance.history.service.operation;

import com.smirnoff.home.finance.history.model.ProductDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.OperationHistoryRepository;
import com.smirnoff.home.finance.history.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationHistoryServiceImpl implements OperationHistoryService {
    private final ProductService productService;
    private final OperationHistoryRepository operationHistoryRepository;

    @Override
    public void save(OperationHistoryEntity operation) {
//        validateSource(operation);


        operationHistoryRepository.save(operation);
    }

    @Override
    public List<OperationHistoryEntity> getAll() {
        return operationHistoryRepository.findAll();
    }

    private void validateSource(OperationHistoryEntity operation) {
        String sourceProduct = operation.getSourceProduct();
        ProductDto product = productService.getById(sourceProduct);

    }
}
