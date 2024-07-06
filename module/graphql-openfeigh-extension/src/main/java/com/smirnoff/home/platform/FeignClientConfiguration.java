package com.smirnoff.home.platform;

import com.smirnoff.home.platform.logging.CustomFeignRequestLogging;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "home-project.http.client.feign.enabled", havingValue = "true")
public class FeignClientConfiguration {

    @Bean
    public CustomFeignRequestLogging customFeignRequestLogging() {
        return new CustomFeignRequestLogging();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
