package com.smirnoff.home.finance.rate.procedure.integration.impl.stockrategrabber.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class StockRateGrabberChannelConfiguration {

    @Bean("stock.rate.in")
    public MessageChannel messageInChannel() {
        return new DirectChannel();
    }

    @Bean("stock.price.yahoo.out")
    public MessageChannel yahooOut() {
        return new DirectChannel();
    }

    @Bean("stock.by.rate.out")
    public MessageChannel messageOutChannel() {
        return new DirectChannel();
    }
}
