package com.smirnoff.home.platform.user.profile.service.user.lc;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;

public interface UserProfileLifecycle {
    UserProfileEntity createNewByEmail(String email);
}
