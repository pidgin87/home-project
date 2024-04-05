package com.smirnoff.home.finance.history.service.service;

import com.smirnoff.home.finance.history.adapter.product.ProductAdapter;
import com.smirnoff.home.finance.history.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductAdapter productAdapter;

    @Override
    public ProductDto getById(String productId) {
        return productAdapter.getById(productId);
    }
}
