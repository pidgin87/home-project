package com.smirnoff.home.garden.iot.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DeviceServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(DeviceServiceRunner.class, args);
    }
}
