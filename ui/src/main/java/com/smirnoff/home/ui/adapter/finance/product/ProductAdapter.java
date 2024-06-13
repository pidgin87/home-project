package com.smirnoff.home.ui.adapter.finance.product;

import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.finance.product.model.ProductTypeModel;

import java.util.List;

public interface ProductAdapter {
    List<ProductModel> getList();

    void create(String productName, ProductTypeModel productType);

    void delete(ProductModel product);
}
