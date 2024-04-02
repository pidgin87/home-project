package com.smirnoff.home.platform.dictionary.service;

import com.smirnoff.home.platform.dictionary.persistance.entity.CurrencyEntity;

import java.util.List;

public interface CurrencyService {
    List<CurrencyEntity> getAll();
}
