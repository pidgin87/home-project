package com.smirnoff.home.ui.service.finance.dictionary;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.platform.dictionary.client.DictionaryClient;
import com.smirnoff.home.platform.dictionary.client.model.GetCurrencyModelList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryClient dictionaryClient;

    //language=graphql
    private static final String GET_FUND_REQUEST = """
            query GetCurrencyList {
                getCurrencyList {
                    id
                    name
                    iso
                }
            }
            """;

    @Override
    public List<CurrencyModel> getCurrencies() {
        GraphQlResponse<GetCurrencyModelList> response = dictionaryClient.getCurrencies(GraphQlRequest.builder()
                .query(GET_FUND_REQUEST)
                .operationName("GetCurrencyList")
                .build()
        );

        return response.getData().getCurrencyList();
    }
}
