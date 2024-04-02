package com.smirnoff.home.finance.product.service;

import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import com.smirnoff.home.finance.product.persistance.entity.ProductType;
import com.smirnoff.home.finance.product.persistance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity create(String name, ProductType type) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setType(type);
        return productRepository.save(product);
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<ProductEntity> getById(String productId) {
        return productRepository.findById(productId);
    }
}
