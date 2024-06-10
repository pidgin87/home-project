package com.smirnoff.home.platform.dictionary.controller;

import com.smirnoff.home.platform.dictionary.dto.currencypair.CurrencyPairModel;
import com.smirnoff.home.platform.dictionary.mapper.CurrencyPairMapper;
import com.smirnoff.home.platform.dictionary.persistance.entity.currencypair.CurrencyPairEntity;
import com.smirnoff.home.platform.dictionary.service.currencypair.CurrencyPairService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CurrencyPairController {

    private final CurrencyPairService currencyPairService;
    private final CurrencyPairMapper currencyPairMapper;

    @QueryMapping
    public List<CurrencyPairModel> getCurrencyPairList() {
        List<CurrencyPairEntity> currencies = currencyPairService.getAll();
        return currencyPairMapper.map(currencies);
    }

}
