package com.smirnoff.home.finance.history.service.service.fund;

import com.smirnoff.home.finance.fund.model.Fund;

import java.util.List;

public interface FundService {
    List<Fund> getByIds(List<String> fundIds);
}
