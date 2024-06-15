package com.smirnoff.home.finance.history.service.balance.lc;

import com.smirnoff.home.finance.history.persistance.entity.ProductBalanceEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductBalanceLifeCycleImpl implements ProductBalanceLifeCycle {
    @Override
    public ProductBalanceEntity createNew() {
        return new ProductBalanceEntity();
    }
}
