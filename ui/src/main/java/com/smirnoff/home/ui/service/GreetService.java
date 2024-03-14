package com.smirnoff.home.ui.service;

import org.springframework.stereotype.Component;

@Component
public class GreetService {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }
}
