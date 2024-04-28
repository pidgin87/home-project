package com.smirnoff.home.ui.adapter.finance.fund;

import com.smirnoff.home.finance.fund.model.Fund;

import java.util.List;

public interface FundAdapter {
    void create(String fundName);

    void update(String id, String name);

    List<Fund> getList();

    void delete(String fundId);
}
