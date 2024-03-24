package com.smirnoff.home.ui.adapter.finance.product;

import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;

import java.util.List;

public interface ProductAdapter {
    List<ProductModel> getList();

    void create(String productName, ProductTypeModel productType);
}
