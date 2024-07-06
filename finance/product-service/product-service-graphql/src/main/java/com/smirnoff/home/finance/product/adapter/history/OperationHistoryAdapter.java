package com.smirnoff.home.finance.product.adapter.history;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;

import java.util.List;

public interface OperationHistoryAdapter {
    List<ProductBalanceDto> getList(List<String> productIds);
}
