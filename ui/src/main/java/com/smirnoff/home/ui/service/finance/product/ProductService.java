package com.smirnoff.home.ui.service.finance.product;

import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.finance.product.model.ProductTypeModel;

import java.util.List;

public interface ProductService {
    void update(String id, String productName);

    void delete(ProductModel product);

    void create(String productName, ProductTypeModel productType);

    List<ProductModel> getList();
}
