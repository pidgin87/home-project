package com.smirnoff.home.finance.product.model;

public record ProductModel(
        String id,
        String name,
        ProductTypeModel type) {

    public boolean isNotNull() {
        return id != null;
    }
}
