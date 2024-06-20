package com.smirnoff.home.finance.history.service.service.product;

import com.smirnoff.home.finance.product.model.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getByIds(List<String> productList);
}
