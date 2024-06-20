package com.smirnoff.home.finance.history.adapter.fund;

import com.smirnoff.home.finance.fund.model.Fund;

import java.util.List;

public interface FundAdapter {
    List<Fund> getByIds(List<String> productList);
}
