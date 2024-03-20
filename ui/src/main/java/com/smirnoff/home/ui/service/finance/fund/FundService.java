package com.smirnoff.home.ui.service.finance.fund;

import com.smirnoff.home.ui.model.finance.fund.FundModel;

import java.util.List;

public interface FundService {
    void create(String fundName);

    void update(FundModel fund);

    List<FundModel> getList();

    void delete(FundModel fund);
}
