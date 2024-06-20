package com.smirnoff.home.finance.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableCaching
@EnableFeignClients(value = {
        "com.smirnoff.home.platform",
        "com.smirnoff.home.finance.history",
        "com.smirnoff.home.finance.product",
        "com.smirnoff.home.finance.fund"
})
@ComponentScan(value = {
        "com.smirnoff.home.platform",
        "com.smirnoff.home.finance.history"
})
public class HistoryApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(HistoryApplicationRunner.class, args);
    }
}
