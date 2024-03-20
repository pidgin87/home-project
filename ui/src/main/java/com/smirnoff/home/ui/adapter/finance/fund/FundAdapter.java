package com.smirnoff.home.ui.adapter.finance.fund;

import com.smirnoff.home.ui.model.finance.fund.FundModel;

import java.util.List;

public interface FundAdapter {
    void createNew(String fundName);

    List<FundModel> getList();

    void delete(String fundId);
}
