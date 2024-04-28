package com.smirnoff.home.ui.model.security;

public record UserModel(
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