package com.smirnoff.home.finance.history.service.service.product;

import com.smirnoff.home.finance.history.adapter.product.ProductAdapter;
import com.smirnoff.home.finance.product.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductAdapter productAdapter;

    @Override
    public List<ProductModel> getByIds(List<String> productList) {
        return productAdapter.getByIds(productList);
    }
}
