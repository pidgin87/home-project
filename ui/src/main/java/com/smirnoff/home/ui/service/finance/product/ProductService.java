package com.smirnoff.home.ui.service.finance.product;

import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;

import java.util.List;

public interface ProductService {
    void update(String id, String productName);

    void delete(ProductModel product);

    void create(String productName, ProductTypeModel productType);

    List<ProductModel> getList();
}
