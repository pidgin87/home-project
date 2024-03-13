package com.smirnoff.home.finance.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.smirnoff.home.platform")
public class FundApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(FundApplicationRunner.class, args);
    }
}
