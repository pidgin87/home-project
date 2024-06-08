package com.smirnoff.home.finance.rate.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@ComponentScan(value = {
        "com.smirnoff.home.platform",
        "com.smirnoff.home.finance.rate.graphql"
})
@EnableFeignClients(value = {
        "com.smirnoff.home.platform"
})
public class GraphQlApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(GraphQlApplicationRunner.class, args);
    }
}
