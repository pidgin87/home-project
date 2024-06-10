package com.smirnoff.home.finance.rate.procedure.integration.gateway;

import com.smirnoff.home.platform.dictionary.dto.currencypair.CurrencyPairModel;
import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "rateIntegrationGateway")
public interface RateIntegrationGateway {

    @Gateway(requestChannel = "stock.rate.in")
    void produceStock(StockModel stockModel);

    @Gateway(requestChannel = "currency.pair.rate.in")
    void produceCurrencyPair(CurrencyPairModel currencyPairModel);
}
