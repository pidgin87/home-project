package com.smirnoff.home.finance.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@ComponentScan(value = {"com.smirnoff.home.platform", "com.smirnoff.home.finance.product"})
public class ProductApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplicationRunner.class, args);
    }
}