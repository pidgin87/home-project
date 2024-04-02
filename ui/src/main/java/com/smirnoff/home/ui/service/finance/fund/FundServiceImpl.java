package com.smirnoff.home.ui.service.finance.fund;

import com.smirnoff.home.ui.adapter.finance.fund.FundAdapter;
import com.smirnoff.home.ui.model.finance.fund.FundModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {
    private final FundAdapter fundAdapter;

    @Override
    public void create(String fundName) {
        fundAdapter.create(fundName);
    }

    @Override
    public void update(FundModel fund) {
        fundAdapter.update(fund.id(), fund.name());
    }

    @Override
    public List<FundModel> getList() {
        return fundAdapter.getList();
    }

    @Override
    public void delete(FundModel fund) {
        fundAdapter.delete(fund.id());
    }
}
