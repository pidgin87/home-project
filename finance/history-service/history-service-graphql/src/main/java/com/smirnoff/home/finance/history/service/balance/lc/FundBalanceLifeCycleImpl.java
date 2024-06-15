package com.smirnoff.home.finance.history.service.balance.lc;

import com.smirnoff.home.finance.history.persistance.entity.FundBalanceEntity;
import org.springframework.stereotype.Component;

@Component
public class FundBalanceLifeCycleImpl implements FundBalanceLifeCycle {
    @Override
    public FundBalanceEntity createNew() {
        return new FundBalanceEntity();
    }
}
