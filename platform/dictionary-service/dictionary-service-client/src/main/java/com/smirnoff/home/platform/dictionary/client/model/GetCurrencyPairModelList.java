package com.smirnoff.home.platform.dictionary.client.model;

import com.smirnoff.home.platform.dictionary.dto.currencypair.CurrencyPairModel;

import java.util.List;

public record GetCurrencyPairModelList(
        List<CurrencyPairModel> getCurrencyPairList
) {
}
