package com.smirnoff.home.platform.user.profile.service.lc;

import com.smirnoff.home.platform.user.profile.persistance.entity.CompanyEntity;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class UserProfileLifecycleImpl implements UserProfileLifecycle {
    @Override
    public UserProfileEntity createNewByEmail(String email) {
        UserProfileEntity profile = new UserProfileEntity();
        profile.setEmail(email);
        profile.setCompany(new CompanyEntity());
        return profile;
    }
}
