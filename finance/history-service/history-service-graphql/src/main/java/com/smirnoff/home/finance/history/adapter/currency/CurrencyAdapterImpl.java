package com.smirnoff.home.finance.history.adapter.currency;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.platform.dictionary.client.DictionaryClient;
import com.smirnoff.home.platform.dictionary.client.model.GetCurrencyModelList;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CurrencyAdapterImpl implements CurrencyAdapter {

    private final DictionaryClient dictionaryClient;

    //language=graphql
    private static final String GET_CURRENCIES_REQUEST = """
            query GetCurrency {
                getCurrencyList {
                    id
                    iso
                    symbol
                }
            }
            """;

    @Override
    @Cacheable("currency-model-cache")
    public List<CurrencyModel> getAll() {
        GetCurrencyModelList currency = dictionaryClient.getCurrencies(GraphQlRequest.builder()
                .query(GET_CURRENCIES_REQUEST)
                .operationName("GetCurrency")
                .build()
        ).getData();

        if (isNull(currency) || isNull(currency.getCurrencyList())) {
            return Collections.emptyList();
        }
        return currency.getCurrencyList();
    }
}
