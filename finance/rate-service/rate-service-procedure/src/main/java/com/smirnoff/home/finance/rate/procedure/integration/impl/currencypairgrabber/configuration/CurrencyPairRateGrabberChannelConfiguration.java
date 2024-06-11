package com.smirnoff.home.finance.rate.procedure.integration.impl.currencypairgrabber.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class CurrencyPairRateGrabberChannelConfiguration {
    @Bean("currency.pair.rate.in")
    public MessageChannel messageInChannel() {
        return new DirectChannel();
    }

    @Bean("currency.pair.price.yahoo.out")
    public MessageChannel yahooOut() {
        return new DirectChannel();
    }

    @Bean("currency.pair.rate.out")
    public MessageChannel messageOutChannel() {
        return new DirectChannel();
    }
}
