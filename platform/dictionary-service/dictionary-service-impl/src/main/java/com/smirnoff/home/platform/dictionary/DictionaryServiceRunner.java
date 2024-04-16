package com.smirnoff.home.platform.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {
        "com.smirnoff.home.platform"
})
@SpringBootApplication
public class DictionaryServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(DictionaryServiceRunner.class, args);
    }
}
