package com.smirnoff.home.platform.dictionary.controller;

import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import com.smirnoff.home.platform.dictionary.mapper.StockMapper;
import com.smirnoff.home.platform.dictionary.persistance.entity.StockEntity;
import com.smirnoff.home.platform.dictionary.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    @QueryMapping
    public List<StockModel> getStockList() {
        List<StockEntity> stocks = stockService.getAll();
        return stockMapper.mapList(stocks);
    }
}
