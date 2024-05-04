package com.smirnoff.home.platform.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {
        "com.smirnoff.home.platform"
})
@EnableFeignClients(value = {
        "com.smirnoff.home.platform"
})
@SpringBootApplication
public class SessionServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(SessionServiceRunner.class, args);
    }
}
