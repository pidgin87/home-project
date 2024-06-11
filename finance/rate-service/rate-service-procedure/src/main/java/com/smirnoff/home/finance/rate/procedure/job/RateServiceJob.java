package com.smirnoff.home.finance.rate.procedure.job;

import com.smirnoff.home.finance.rate.procedure.adapter.DictionaryAdapter;
import com.smirnoff.home.finance.rate.procedure.integration.gateway.RateIntegrationGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RateServiceJob {

    private final DictionaryAdapter dictionaryAdapter;
    private final RateIntegrationGateway rateIntegrationGateway;

    @Scheduled(cron = "0 */10 * * * *")
    public void runStocks() {
        dictionaryAdapter.getStocks().stream()
                .filter(Objects::nonNull)
                .peek(model -> log.debug("stock is [id:{}, ticker:{}]", model.getId(), model.getTicker()))
                .peek(rateIntegrationGateway::produceStock)
                .toList();
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void runCurrencyPairs() {
        dictionaryAdapter.getCurrencyPair().stream()
                .filter(Objects::nonNull)
                .peek(model -> log.debug("currency pair is [id:{}, ticker:{}]", model.getId(), model.getTicker()))
                .peek(rateIntegrationGateway::produceCurrencyPair)
                .toList();
    }
}
