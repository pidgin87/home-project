package com.smirnoff.home.platform.dictionary.service.currency;

import com.smirnoff.home.platform.dictionary.persistance.entity.currency.CurrencyEntity;

import java.util.List;

public interface CurrencyService {
    List<CurrencyEntity> getAll();
}
