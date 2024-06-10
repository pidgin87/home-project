package com.smirnoff.home.platform.dictionary.service.currencypair;

import com.smirnoff.home.platform.dictionary.persistance.entity.currencypair.CurrencyPairEntity;
import com.smirnoff.home.platform.dictionary.persistance.repository.CurrencyPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyPairServiceImpl implements CurrencyPairService {
    private final CurrencyPairRepository currencyPairRepository;

    @Override
    public List<CurrencyPairEntity> getAll() {
        return currencyPairRepository.findAll(Sort.by("id"));
    }
}
