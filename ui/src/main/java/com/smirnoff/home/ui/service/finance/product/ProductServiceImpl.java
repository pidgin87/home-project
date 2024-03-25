package com.smirnoff.home.ui.service.finance.product;

import com.smirnoff.home.ui.adapter.finance.product.ProductAdapter;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductAdapter productAdapter;
    @Override
    public void update(String id, String productName) {

    }

    @Override
    public void delete(ProductModel product) {
        productAdapter.delete(product);
    }

    @Override
    public void create(String productName, ProductTypeModel productType) {
        productAdapter.create(productName, productType);
    }

    @Override
    public List<ProductModel> getList() {
        return productAdapter.getList();
    }
}
