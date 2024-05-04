package com.smirnoff.home.platform.session.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileSession {
    private String id;
    private UserProfileCompanySession company;
}
