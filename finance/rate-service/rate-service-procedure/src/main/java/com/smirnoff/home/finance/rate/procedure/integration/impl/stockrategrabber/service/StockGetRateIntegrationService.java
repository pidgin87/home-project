package com.smirnoff.home.finance.rate.procedure.integration.impl.stockrategrabber.service;

import com.smirnoff.home.finance.rate.procedure.integration.gateway.JpaGateway;
import com.smirnoff.home.finance.rate.procedure.integration.impl.stockrategrabber.service.mapper.StockRateEntityMapper;
import com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance.FinanceYahooDto;
import com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance.FinanceYahooResultMetaDto;
import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
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
public class StockGetRateIntegrationService {
    private final JpaGateway jpaGateway;

    private final StockRateEntityMapper stockRateEntityMapper;

    private static final String TICKER_HEADER = "ticker";
    private static final String CURRENCY_HEADER = "currency";
    private static final String STOCKID_HEADER = "stockId";

    @SneakyThrows
    @ServiceActivator(inputChannel = "stock.rate.in", outputChannel = "yahoo.finance.http.request.input.channel")
    public Message<StockModel> findRateByStock(Message<StockModel> message) {
        StockModel payload = message.getPayload();
        String ticker = payload.getTicker();
        log.debug("message -> {}", ticker);

        return MessageBuilder.fromMessage(message)
                .setReplyChannelName("stock.price.yahoo.out")
                .copyHeaders(Map.of(
                        TICKER_HEADER, ticker,
                        CURRENCY_HEADER, payload.getCurrency().getId(),
                        STOCKID_HEADER, payload.getId()
                ))
                .build();
    }

    @ServiceActivator(inputChannel = "stock.price.yahoo.out")
    public void mapperEntity(Message<FinanceYahooDto> message) {
        FinanceYahooDto payload = message.getPayload();
        FinanceYahooResultMetaDto meta = payload.getChart().getResult().get(0).getMeta();

        MessageHeaders headers = message.getHeaders();

        StockRateEntity entity = stockRateEntityMapper.map(
                meta,
                headers.get(TICKER_HEADER, String.class),
                headers.get(STOCKID_HEADER, String.class),
                headers.get(CURRENCY_HEADER, String.class)
        );

        jpaGateway.persist(entity);
    }
}
