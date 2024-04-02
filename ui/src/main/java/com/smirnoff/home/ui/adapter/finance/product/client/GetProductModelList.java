package com.smirnoff.home.ui.adapter.finance.product.client;

import com.smirnoff.home.ui.model.finance.product.ProductModel;

import java.util.List;

public record GetProductModelList(
        List<ProductModel> getProductList
) {
}
