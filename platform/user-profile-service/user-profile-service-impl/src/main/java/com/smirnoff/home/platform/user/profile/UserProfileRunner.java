package com.smirnoff.home.platform.user.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {
        "com.smirnoff.home.platform"
})
@SpringBootApplication
public class UserProfileRunner {
    public static void main(String[] args) {
        SpringApplication.run(UserProfileRunner.class, args);
    }
}
