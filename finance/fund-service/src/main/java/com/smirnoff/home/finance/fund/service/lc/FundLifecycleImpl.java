package com.smirnoff.home.finance.fund.service.lc;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import org.springframework.stereotype.Component;

@Component
public class FundLifecycleImpl implements FundLifecycle {
    @Override
    public FundEntity create() {
        return new FundEntity();
    }
}
