package com.smirnoff.home.platform.dictionary.service.stock;

import com.smirnoff.home.platform.dictionary.persistance.entity.stock.StockEntity;
import com.smirnoff.home.platform.dictionary.persistance.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    public List<StockEntity> getAll() {
        return stockRepository.findAll();
    }
}
