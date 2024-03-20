package com.smirnoff.home.ui.configuration.feigh;

import com.smirnoff.home.ui.adapter.finance.fund.client.FundClient;
import feign.Feign;
import feign.Target;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FundFeighClientConfiguration {

//    @Bean
//    public FundClient fundClient() {
//        return Feign.builder()
//                .target(Target.EmptyTarget.create(FundClient.class));
//    }
}
