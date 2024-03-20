package com.smirnoff.home.ui.adapter.finance.fund.client;

import com.smirnoff.home.ui.model.finance.fund.FundModel;

import java.util.List;

public record GetFundModelList(
        List<FundModel> getFundList
) {
}
