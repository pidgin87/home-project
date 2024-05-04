package com.smirnoff.home.platform.user.profile.service;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import com.smirnoff.home.platform.user.profile.persistance.repository.UserRepository;
import com.smirnoff.home.platform.user.profile.service.lc.UserProfileLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProfileLifecycle userProfileLifecycle;

    @Override
    public Optional<UserProfileEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserProfileEntity createEmptyProfileByEmail(String email) {
        UserProfileEntity userProfileEntity = userProfileLifecycle.createNewByEmail(email);
        return userRepository.saveAndFlush(userProfileEntity);
    }

    @Override
    public Optional<UserProfileEntity> getUserById(String userProfileId) {
        return userRepository.findById(userProfileId);
    }
}
