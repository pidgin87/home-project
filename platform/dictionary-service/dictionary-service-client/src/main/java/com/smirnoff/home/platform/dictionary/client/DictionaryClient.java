package com.smirnoff.home.platform.dictionary.client;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.platform.dictionary.client.model.GetCurrencyModelList;
import com.smirnoff.home.platform.dictionary.client.model.GetCurrencyPairModelList;
import com.smirnoff.home.platform.dictionary.client.model.GetStockModelList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "platform-dictionary-service", path = "/api/platform/dictionary")
public interface DictionaryClient {
    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetCurrencyModelList> getCurrencies(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetStockModelList> getStocks(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetCurrencyPairModelList> getCurrencyPairs(@RequestBody GraphQlRequest request);
}
