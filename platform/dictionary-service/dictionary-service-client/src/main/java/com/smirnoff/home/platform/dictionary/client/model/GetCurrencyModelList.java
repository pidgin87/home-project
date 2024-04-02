package com.smirnoff.home.platform.dictionary.client.model;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;

import java.util.List;

public record GetCurrencyModelList(
        List<CurrencyModel> getCurrencyList
) {
}
