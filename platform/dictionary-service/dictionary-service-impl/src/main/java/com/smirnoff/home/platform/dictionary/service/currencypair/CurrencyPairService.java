package com.smirnoff.home.platform.dictionary.service.currencypair;

import com.smirnoff.home.platform.dictionary.persistance.entity.currencypair.CurrencyPairEntity;

import java.util.List;

public interface CurrencyPairService {
    List<CurrencyPairEntity> getAll();
}
