package com.smirnoff.home.project.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerRunner {
    public static void main(String[] arguments) {
        SpringApplication.run(ConfigServerRunner.class, arguments);
    }
}
