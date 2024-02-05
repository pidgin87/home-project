package com.smirnoff.home.garden.iot.device.command;

import com.tuya.connector.spring.annotations.ConnectorScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@ConnectorScan(basePackages = "com.smirnoff.home.garden.iot.device.command.adapter")
@EnableCaching
@SpringBootApplication
public class DeviceCommandServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(DeviceCommandServiceRunner.class, args);
    }
}
