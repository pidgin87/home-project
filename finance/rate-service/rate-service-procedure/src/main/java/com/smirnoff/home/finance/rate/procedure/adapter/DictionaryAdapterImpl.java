package com.smirnoff.home.finance.rate.procedure.adapter;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.platform.dictionary.client.DictionaryClient;
import com.smirnoff.home.platform.dictionary.client.model.GetStockModelList;
import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class DictionaryAdapterImpl implements DictionaryAdapter {
    private final DictionaryClient dictionaryClient;

    //language=graphql
    private static final String GET_STOCKS_REQUEST = """
            query GetStockList {
                getStockList {
                    id
                    ticker
                }
            }
            """;

    @Override
    public List<StockModel> getStocks() {
        GetStockModelList stockList = dictionaryClient.getStocks(GraphQlRequest.builder()
                .query(GET_STOCKS_REQUEST)
                .operationName("GetStockList")
                .build()
        ).getData();

        if (Objects.isNull(stockList)) {
            return emptyList();
        }

        return stockList.getStockList();
    }
}
