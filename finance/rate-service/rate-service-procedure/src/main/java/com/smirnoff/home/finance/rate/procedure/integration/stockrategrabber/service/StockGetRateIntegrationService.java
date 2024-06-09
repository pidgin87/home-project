package com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.service;

import com.smirnoff.home.finance.rate.procedure.integration.gateway.JpaGateway;
import com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.model.FinanceYahooDto;
import com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.model.FinanceYahooResultMetaDto;
import com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.service.mapper.StockRateEntityMapper;
import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
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
    private static final String STOCKID_HEADER = "stockId";

    @SneakyThrows
    @ServiceActivator(inputChannel = "stock.by.rate.in", outputChannel = "yahoo.finance.http.request.input.channel")
    public Message<StockModel> findRateByStock(Message<StockModel> message) {
        StockModel payload = message.getPayload();
        String ticker = payload.getTicker();
        log.debug("message -> {}", ticker);

        return MessageBuilder.fromMessage(message)
                .setReplyChannelName("yahoo.finance.http.request.output.channel")
                .copyHeaders(Map.of(
                        TICKER_HEADER, ticker,
                        STOCKID_HEADER, payload.getId()
                ))
                .build();
    }

    @Bean
    @ServiceActivator(inputChannel = "yahoo.finance.http.request.input.channel")
    public HttpRequestExecutingMessageHandler outbound() {
        HttpRequestExecutingMessageHandler handler = new HttpRequestExecutingMessageHandler("https://query1.finance.yahoo.com/v8/finance/chart/{ticker}");
        handler.setHttpMethod(HttpMethod.GET);
        handler.setExpectedResponseType(FinanceYahooDto.class);
        handler.setRequiresReply(true);
        handler.setUriVariableExpressions(Map.of(
                "ticker", new SpelExpressionParser().parseExpression("headers['ticker']"),
                "region", new ValueExpression<>("US"),
                "lang", new ValueExpression<>("en-US"),
                "includePrePost", new ValueExpression<>("false"),
                "interval", new ValueExpression<>("1h"),
                "range", new ValueExpression<>("1h"),
                "corsDomain", new ValueExpression<>("finance.yahoo.com"),
                ".tsrc", new ValueExpression<>("finance")
        ));
        return handler;
    }

    @ServiceActivator(inputChannel = "yahoo.finance.http.request.output.channel")
    public void mapperEntity(Message<FinanceYahooDto> message) {
        FinanceYahooDto payload = message.getPayload();
        FinanceYahooResultMetaDto meta = payload.getChart().getResult().get(0).getMeta();

        MessageHeaders headers = message.getHeaders();

        StockRateEntity entity = stockRateEntityMapper.map(
                meta,
                headers.get(TICKER_HEADER, String.class),
                headers.get(STOCKID_HEADER, String.class)
        );

        jpaGateway.persist(entity);
    }
}
