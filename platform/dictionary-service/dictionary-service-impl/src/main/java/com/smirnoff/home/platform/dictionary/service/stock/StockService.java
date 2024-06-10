package com.smirnoff.home.platform.dictionary.service.stock;

import com.smirnoff.home.platform.dictionary.persistance.entity.stock.StockEntity;

import java.util.List;

public interface StockService {
    List<StockEntity> getAll();
}
