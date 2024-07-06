package com.smirnoff.home.finance.history.service.balance;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.entity.ProductBalanceEntity;
import com.smirnoff.home.finance.history.persistance.repository.ProductBalanceRepository;
import com.smirnoff.home.finance.history.persistance.repository.projections.ProductBalanceProjection;
import com.smirnoff.home.finance.history.service.balance.lc.ProductBalanceLifeCycle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class BalanceProductServiceImpl implements BalanceProductService {
    private final ProductBalanceLifeCycle productBalanceLifeCycle;
    private final ProductBalanceRepository productBalanceRepository;

    @Override
    public void createByOperation(OperationHistoryEntity operation) {
        if (nonNull(operation.getSourceProduct())) {
            productBalanceRepository.save(createProductBalance(
                    operation,
                    operation.getSourceProduct(),
                    operation.getSourceAmount().multiply(BigDecimal.valueOf(-1L)),
                    operation.getSourceCurrency()
            ));
        }

        if (nonNull(operation.getDestinationProduct())) {
            productBalanceRepository.save(createProductBalance(
                    operation,
                    operation.getDestinationProduct(),
                    operation.getDestinationAmount(),
                    operation.getDestinationCurrency()
            ));
        }
    }

    @Override
    public List<ProductBalanceProjection> getByIds(List<String> productIds) {
        return productBalanceRepository.getBalanceByProduct(productIds);
    }

    private ProductBalanceEntity createProductBalance(OperationHistoryEntity operation,
                                                      String product,
                                                      BigDecimal amount,
                                                      String currency) {
        ProductBalanceEntity negativeOperation = productBalanceLifeCycle.createNew();
        negativeOperation.setOperation(operation);
        negativeOperation.setProduct(product);
        negativeOperation.setAmount(amount);
        negativeOperation.setCurrency(currency);
        return negativeOperation;
    }
}
