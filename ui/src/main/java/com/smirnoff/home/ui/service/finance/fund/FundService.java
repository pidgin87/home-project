package com.smirnoff.home.ui.service.finance.fund;

import com.smirnoff.home.finance.fund.model.Fund;

import java.util.List;

public interface FundService {
    void create(String fundName);

    void update(Fund fund);

    List<Fund> getList();

    void delete(Fund fund);
}
