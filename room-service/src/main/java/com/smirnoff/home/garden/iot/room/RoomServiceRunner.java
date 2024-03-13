package com.smirnoff.home.garden.iot.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(value = {"com.smirnoff.home.platform", "com.smirnoff.home.garden.iot.room"})
public class RoomServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(RoomServiceRunner.class, args);
    }
}
