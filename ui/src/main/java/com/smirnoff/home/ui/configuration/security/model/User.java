package com.smirnoff.home.ui.configuration.security.model;

public record User (
        String firstName,
        String lastName,
        String email,
        String picture
) {
    @Override
    public String toString() {
        return firstName;
    }
}