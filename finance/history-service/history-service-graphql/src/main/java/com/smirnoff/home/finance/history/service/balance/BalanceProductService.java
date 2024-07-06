package com.smirnoff.home.finance.history.service.balance;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.projections.ProductBalanceProjection;

import java.util.List;

public interface BalanceProductService {
    void createByOperation(OperationHistoryEntity operation);

    List<ProductBalanceProjection> getByIds(List<String> productIds);
}
