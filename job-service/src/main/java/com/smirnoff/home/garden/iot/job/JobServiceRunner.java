package com.smirnoff.home.garden.iot.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smirnoff.home.platform")
public class JobServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(JobServiceRunner.class, args);
    }
}
