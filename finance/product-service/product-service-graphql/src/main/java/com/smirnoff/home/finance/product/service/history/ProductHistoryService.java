package com.smirnoff.home.finance.product.service.history;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;

import java.util.List;
import java.util.Map;

public interface ProductHistoryService {
    Map<String, ProductBalanceDto> getAmountByProducts(List<String> productIds);
}
