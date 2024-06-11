package com.smirnoff.home.finance.rate.procedure.integration.service;

import com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance.FinanceYahooDto;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class YahooFinanceGetPriceByTickerService {

    @Bean("yahoo.finance.http.request.input.channel")
    public MessageChannel yahooIn() {
        return new DirectChannel();
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
}
