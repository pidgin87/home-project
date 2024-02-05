package com.smirnoff.home.garden.iot.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApiServiceDiscoveryRunner {
    public static void main(String[] args) {
        SpringApplication.run(ApiServiceDiscoveryRunner.class, args);
    }
}
