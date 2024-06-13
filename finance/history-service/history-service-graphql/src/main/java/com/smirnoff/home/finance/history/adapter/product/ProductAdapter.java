package com.smirnoff.home.finance.history.adapter.product;

import com.smirnoff.home.finance.history.model.ProductDto;

public interface ProductAdapter {
    ProductDto getById(String productId);
}
