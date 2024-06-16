package com.smirnoff.home.finance.product.service.product;

import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import com.smirnoff.home.finance.product.persistance.entity.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductEntity> getAll();

    ProductEntity create(String name, ProductType type);

    void delete(String productId);

    Optional<ProductEntity> getById(String productId);

    List<ProductEntity> getAll(List<String> productIds);
}
