package com.smirnoff.home.platform.dictionary.service.currency;

import com.smirnoff.home.platform.dictionary.persistance.entity.currency.CurrencyEntity;
import com.smirnoff.home.platform.dictionary.persistance.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyEntity> getAll() {
        return currencyRepository.findAll(
                Sort.by(Sort.Direction.ASC, "direction")
        );
    }
}
