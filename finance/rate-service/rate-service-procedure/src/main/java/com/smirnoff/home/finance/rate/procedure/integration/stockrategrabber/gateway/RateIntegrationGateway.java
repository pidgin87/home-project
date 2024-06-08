package com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.gateway;

import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "rateIntegrationGateway")
public interface RateIntegrationGateway {

    @Gateway(requestChannel = "stock.by.rate.in")
    void produce(StockModel stockModel);
}
