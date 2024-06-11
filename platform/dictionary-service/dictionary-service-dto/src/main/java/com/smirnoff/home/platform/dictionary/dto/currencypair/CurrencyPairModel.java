package com.smirnoff.home.platform.dictionary.dto.currencypair;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyPairModel {
    private String id;
    private CurrencyModel left;
    private CurrencyModel right;
    private String ticker;
}
