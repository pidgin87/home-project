package com.smirnoff.home.ui.service.finance.fund;

import com.smirnoff.home.ui.adapter.finance.fund.FundAdapter;
import org.springframework.stereotype.Component;

@Component
public class FundServiceImpl implements FundService {
    private final FundAdapter fundAdapter;

    public FundServiceImpl(FundAdapter fundAdapter) {
        this.fundAdapter = fundAdapter;
    }
}
