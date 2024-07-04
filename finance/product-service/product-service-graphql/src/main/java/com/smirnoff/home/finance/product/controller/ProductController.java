package com.smirnoff.home.finance.product.controller;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;
import com.smirnoff.home.finance.product.mapper.BalanceMapper;
import com.smirnoff.home.finance.product.mapper.ProductMapper;
import com.smirnoff.home.finance.product.model.AmountModel;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.finance.product.model.VoidResponse;
import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import com.smirnoff.home.finance.product.persistance.entity.ProductType;
import com.smirnoff.home.finance.product.service.history.ProductHistoryService;
import com.smirnoff.home.finance.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductHistoryService productHistoryService;

    private final ProductMapper productMapper;
    private final BalanceMapper balanceMapper;

    @QueryMapping
    public List<ProductModel> getProductList() {
        return productMapper.map(
                productService.getAll()
        );
    }

    @BatchMapping(typeName = "Product", field = "amount")
    public Map<ProductModel, AmountModel> sourceProduct(List<ProductModel> products) {
        List<String> productIds = products.stream().map(ProductModel::id).toList();
        Map<String, ProductBalanceDto> balances = productHistoryService.getAmountByProducts(productIds);

        Map<ProductModel, AmountModel> values = new HashMap<>();
        for (ProductModel product : products) {
            ProductBalanceDto balanceByProduct = balances.get(product.id());
            values.put(product, balanceMapper.map(balanceByProduct));
        }
        return values;
    }

    @QueryMapping
    public List<ProductModel> getProductListByIds(@Argument List<String> productIds) {
        return productMapper.map(
                productService.getAll(productIds)
        );
    }

    @QueryMapping
    public ProductModel getProduct(@Argument String productId) {
        Optional<ProductEntity> product = productService.getById(productId);
        return productMapper.map(product.get());
    }

    @MutationMapping
    public ProductModel createProduct(@Argument String name, @Argument ProductType type) {
        return productMapper.map(
                productService.create(name, type)
        );
    }

    @MutationMapping
    public VoidResponse deleteProduct(@Argument String productId) {
        productService.delete(productId);
        return new VoidResponse();
    }
}
