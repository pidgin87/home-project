package com.smirnoff.home.finance.history.service.currency;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;

import java.util.Collection;
import java.util.List;

public interface CurrencyExternalService {
    CurrencyModel get(String id);

    List<CurrencyModel> getBy(Collection<String> currencyIds);
}
