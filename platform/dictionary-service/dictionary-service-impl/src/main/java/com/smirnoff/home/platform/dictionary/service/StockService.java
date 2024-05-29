package com.smirnoff.home.platform.dictionary.service;

import com.smirnoff.home.platform.dictionary.persistance.entity.StockEntity;

import java.util.List;

public interface StockService {
    List<StockEntity> getAll();
}
