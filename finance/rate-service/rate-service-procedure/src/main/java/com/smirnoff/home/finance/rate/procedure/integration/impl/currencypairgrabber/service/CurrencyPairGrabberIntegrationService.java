package com.smirnoff.home.finance.rate.procedure.integration.impl.currencypairgrabber.service;

import com.smirnoff.home.finance.rate.procedure.integration.gateway.JpaGateway;
import com.smirnoff.home.finance.rate.procedure.integration.impl.currencypairgrabber.service.mapper.CurrencyPairRateEntityMapper;
import com.smirnoff.home.finance.rate.procedure.integration.impl.stockrategrabber.service.mapper.StockRateEntityMapper;
import com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance.FinanceYahooDto;
import com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance.FinanceYahooResultMetaDto;
import com.smirnoff.home.finance.rate.procedure.persist.model.CurrencyPairRateEntity;
import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
import com.smirnoff.home.platform.dictionary.dto.currencypair.CurrencyPairModel;
import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyPairGrabberIntegrationService {
    private final JpaGateway jpaGateway;

    private final CurrencyPairRateEntityMapper currencyPairRateEntityMapper;

    private static final String TICKER_HEADER = "ticker";
    private static final String CURRENCY_HEADER = "currency";
    private static final String STOCKID_HEADER = "stockId";

    @SneakyThrows
    @ServiceActivator(inputChannel = "currency.pair.rate.in", outputChannel = "yahoo.finance.http.request.input.channel")
    public Message<CurrencyPairModel> findRateByStock(Message<CurrencyPairModel> message) {
        CurrencyPairModel payload = message.getPayload();
        String ticker = payload.getTicker();
        log.debug("message -> {}", ticker);

        return MessageBuilder.fromMessage(message)
                .setReplyChannelName("currency.pair.price.yahoo.out")
                .copyHeaders(Map.of(
                        TICKER_HEADER, ticker,
                        STOCKID_HEADER, payload.getId()
                ))
                .build();
    }

    @ServiceActivator(inputChannel = "currency.pair.price.yahoo.out")
    public void mapperEntity(Message<FinanceYahooDto> message) {
        FinanceYahooDto payload = message.getPayload();
        FinanceYahooResultMetaDto meta = payload.getChart().getResult().get(0).getMeta();

        MessageHeaders headers = message.getHeaders();

        CurrencyPairRateEntity entity = currencyPairRateEntityMapper.map(
                meta,
                headers.get(TICKER_HEADER, String.class),
                headers.get(STOCKID_HEADER, String.class)
        );

        jpaGateway.persist(entity);
    }
}
