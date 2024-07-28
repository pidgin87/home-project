package com.smirnoff.home.platform.user.profile.service.user;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserProfileEntity> getUserByEmail(String email);

    UserProfileEntity createEmptyProfileByEmail(String email);

    Optional<UserProfileEntity> getUserById(String userProfileId);
}
