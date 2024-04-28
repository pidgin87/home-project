package com.smirnoff.home.ui.service.finance.fund;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.ui.adapter.finance.fund.FundAdapter;
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
    public void update(Fund fund) {
        fundAdapter.update(fund.id(), fund.name());
    }

    @Override
    public List<Fund> getList() {
        return fundAdapter.getList();
    }

    @Override
    public void delete(Fund fund) {
        fundAdapter.delete(fund.id());
    }
}
