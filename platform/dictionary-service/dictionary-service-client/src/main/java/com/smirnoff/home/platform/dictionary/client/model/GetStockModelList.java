package com.smirnoff.home.platform.dictionary.client.model;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;

import java.util.List;

public record GetStockModelList(
        List<StockModel> getStockList
) {
}
