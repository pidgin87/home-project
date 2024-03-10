package com.smirnoff.home.finance.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FundApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(FundApplicationRunner.class, args);
    }
}
