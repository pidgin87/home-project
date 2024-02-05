package com.smirnoff.home.garden.iot.device.contoller;

import com.smirnoff.home.garden.iot.device.mapper.ProductMapper;
import com.smirnoff.home.garden.iot.device.model.Product;
import com.smirnoff.home.garden.iot.device.persistance.model.ProductEntity;
import com.smirnoff.home.garden.iot.device.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @QueryMapping
    public List<Product> getProducts() {
        List<ProductEntity> products = productService.findAll();
        return productMapper.map(products);
    }

}
