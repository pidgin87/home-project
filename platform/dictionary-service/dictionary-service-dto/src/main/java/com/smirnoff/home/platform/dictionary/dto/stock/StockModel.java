package com.smirnoff.home.platform.dictionary.dto.stock;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockModel {
    private String id;
    private String ticker;
    private String name;
    private CurrencyModel currency;
}
