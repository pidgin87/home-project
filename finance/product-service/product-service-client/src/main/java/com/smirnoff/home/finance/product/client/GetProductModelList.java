package com.smirnoff.home.finance.product.client;

import com.smirnoff.home.finance.product.model.ProductModel;

import java.util.List;

public record GetProductModelList(
        List<ProductModel> getProductList
) {
}
