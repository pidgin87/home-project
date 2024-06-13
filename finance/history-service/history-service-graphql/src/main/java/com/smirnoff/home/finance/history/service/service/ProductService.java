package com.smirnoff.home.finance.history.service.service;

import com.smirnoff.home.finance.history.model.ProductDto;

public interface ProductService {
    ProductDto getById(String productId);
}
