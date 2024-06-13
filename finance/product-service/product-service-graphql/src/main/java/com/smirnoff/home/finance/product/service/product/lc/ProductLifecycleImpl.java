package com.smirnoff.home.finance.product.service.product.lc;

import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductLifecycleImpl implements ProductLifecycle {

    private final SessionClientService sessionClientService;

    @Override
    public ProductEntity create() {
        ProductEntity product = new ProductEntity();
        product.setCompanyId(sessionClientService.getCompanyId());
        return product;
    }
}
