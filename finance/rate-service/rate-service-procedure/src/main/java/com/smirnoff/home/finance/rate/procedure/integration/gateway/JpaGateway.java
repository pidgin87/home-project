package com.smirnoff.home.finance.rate.procedure.integration.gateway;

import com.smirnoff.home.finance.rate.procedure.persist.model.CurrencyPairRateEntity;
import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
import jakarta.transaction.Transactional;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "jpaGateway")
public interface JpaGateway {

    @Transactional
    @Gateway(requestChannel = "persist.rate.stock.channel")
    void persist(StockRateEntity entity);

    @Transactional
    @Gateway(requestChannel = "persist.currency.pair.rate.stock.channel")
    void persist(CurrencyPairRateEntity payload);
}
