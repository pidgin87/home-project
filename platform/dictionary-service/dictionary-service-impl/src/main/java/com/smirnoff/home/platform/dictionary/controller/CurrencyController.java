package com.smirnoff.home.platform.dictionary.controller;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.platform.dictionary.mapper.CurrencyMapper;
import com.smirnoff.home.platform.dictionary.persistance.entity.CurrencyEntity;
import com.smirnoff.home.platform.dictionary.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyMapper currencyMapper;

    @QueryMapping
    public List<CurrencyModel> getFundList() {
        List<CurrencyEntity> currencies = currencyService.getAll();
        return currencyMapper.map(currencies);
    }

}
