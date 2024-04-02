package com.smirnoff.home.ui.service.finance.dictionary;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;

import java.util.List;

public interface DictionaryService {
    List<CurrencyModel> getCurrencies();
}
