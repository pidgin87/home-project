package com.smirnoff.home.finance.history.service.currency;

import com.smirnoff.home.finance.history.adapter.currency.CurrencyAdapter;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyExternalServiceImpl implements CurrencyExternalService {

    private final CurrencyAdapter currencyAdapter;

    @Override
    public CurrencyModel get(String currencyId) {
        return currencyAdapter.getAll().stream()
                .filter(currencyDto -> currencyDto.getId().equals(currencyId))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<CurrencyModel> getBy(Collection<String> currencyIds) {
        return currencyAdapter.getAll().stream()
                .filter(currencyDto -> currencyIds.contains(currencyDto.getId()))
                .toList();
    }


}
