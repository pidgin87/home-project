package com.smirnoff.home.ui.adapter.finance.fund;

import com.smirnoff.home.ui.model.finance.fund.FundModel;

import java.util.List;

public interface FundAdapter {
    void create(String fundName);

    void update(String id, String name);

    List<FundModel> getList();

    void delete(String fundId);
}
