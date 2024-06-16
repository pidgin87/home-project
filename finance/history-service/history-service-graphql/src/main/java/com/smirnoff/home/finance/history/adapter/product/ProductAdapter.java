package com.smirnoff.home.finance.history.adapter.product;

import com.smirnoff.home.finance.product.model.ProductModel;

import java.util.List;

public interface ProductAdapter {
    List<ProductModel> getByIds(List<String> productList);
}
