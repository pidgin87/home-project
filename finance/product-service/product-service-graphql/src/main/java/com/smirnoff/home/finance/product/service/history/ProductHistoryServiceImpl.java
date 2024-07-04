package com.smirnoff.home.finance.product.service.history;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;
import com.smirnoff.home.finance.product.adapter.history.OperationHistoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductHistoryServiceImpl implements ProductHistoryService {

    private final OperationHistoryAdapter operationHistoryAdapter;

    @Override
    public Map<String, ProductBalanceDto> getAmountByProducts(List<String> productIds) {
        List<ProductBalanceDto> balances = operationHistoryAdapter.getList(productIds);
        Map<String, ProductBalanceDto> result = new HashMap<>();
        for (ProductBalanceDto balance : balances) {
            result.put(balance.productId(), balance);
        }
        return result;
    }
}
