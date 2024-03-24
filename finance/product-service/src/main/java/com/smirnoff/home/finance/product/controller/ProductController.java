package com.smirnoff.home.finance.product.controller;

import com.smirnoff.home.finance.product.mapper.ProductMapper;
import com.smirnoff.home.finance.product.model.ProductDto;
import com.smirnoff.home.finance.product.persistance.entity.ProductType;
import com.smirnoff.home.finance.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @QueryMapping
    public List<ProductDto> getProductList() {
        return productMapper.map(
                productService.getAll()
        );
    }

    @MutationMapping
    public ProductDto createProduct(@Argument String name, @Argument ProductType type) {
        return productMapper.map(
                productService.create(name, type)
        );
    }
}
