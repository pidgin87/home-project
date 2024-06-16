package com.smirnoff.home.finance.product.service.product;

import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import com.smirnoff.home.finance.product.persistance.entity.ProductType;
import com.smirnoff.home.finance.product.persistance.repository.ProductRepository;
import com.smirnoff.home.finance.product.service.product.lc.ProductLifecycle;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductLifecycle productLifecycle;
    private final SessionClientService sessionClientService;

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findByCompanyIdOrderByCreatedDateAsc(sessionClientService.getCompanyId());
    }

    @Override
    public ProductEntity create(String name, ProductType type) {
        ProductEntity product = productLifecycle.create();
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

    @Override
    public List<ProductEntity> getAll(List<String> productIds) {
        String companyId = sessionClientService.getCompanyId();
        return productRepository.findByCompanyIdAndIdInOrderByCreatedDateAsc(companyId, productIds);
    }
}
