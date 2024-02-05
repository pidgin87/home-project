package com.smirnoff.home.garden.iot.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RoomServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(RoomServiceRunner.class, args);
    }
}
