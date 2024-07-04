package com.smirnoff.home.finance.product.model;

public record ProductModel(
        String id,
        String name,
        ProductTypeModel type,
        AmountModel amount) {

    public boolean isNotNull() {
        return id != null;
    }
}
