package com.smirnoff.home.platform.user.profile.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    private String id;
    private String firstName;
    private String lastName;
    private CompanyModel company;
}
