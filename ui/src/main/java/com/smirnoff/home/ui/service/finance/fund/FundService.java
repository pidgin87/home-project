package com.smirnoff.home.ui.service.finance.fund;

import com.smirnoff.home.ui.model.finance.fund.FundModel;

import java.util.List;

public interface FundService {
    void createNew(String fundName);

    List<FundModel> getList();

    void delete(FundModel fund);
}
