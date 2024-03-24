package com.smirnoff.home.finance.product.model;

import com.smirnoff.home.finance.product.persistance.entity.ProductType;

public record ProductDto(
        String id,
        String name,
        ProductType type
) {
}
