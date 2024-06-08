package com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RateGrabberChannelConfiguration {

    @Bean("stock.by.rate.in")
    public MessageChannel messageInChannel() {
        return new DirectChannel();
    }

    @Bean("yahoo.finance.http.request.input.channel")
    public MessageChannel yahooIn() {
        return new DirectChannel();
    }

    @Bean("yahoo.finance.http.request.output.channel")
    public MessageChannel yahooOut() {
        return new DirectChannel();
    }

    @Bean("stock.by.rate.out")
    public MessageChannel messageOutChannel() {
        return new DirectChannel();
    }
}
