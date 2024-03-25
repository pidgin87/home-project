package com.smirnoff.home.finance.product.service;

import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import com.smirnoff.home.finance.product.persistance.entity.ProductType;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAll();

    ProductEntity create(String name, ProductType type);

    void delete(String productId);
}
