package com.smirnoff.home.finance.rate.procedure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableScheduling
@EnableIntegration
@IntegrationComponentScan(value = {
        "com.smirnoff.home.finance.rate.procedure.integration"
})
@EnableFeignClients(value = {
        "com.smirnoff.home.platform",
})
@ComponentScan(value = {
        "com.smirnoff.home.platform",
        "com.smirnoff.home.finance.rate"
})
public class RateProcedureApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(RateProcedureApplicationRunner.class, args);
    }
}

