package com.smirnoff.home.platform.user.profile.controller;

import com.smirnoff.home.platform.user.profile.mapper.UserMapper;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import com.smirnoff.home.platform.user.profile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @QueryMapping
    public UserProfile getUserByEmail(@Argument String email) {
        UserProfileEntity user = userService.getUserByEmail(email).orElse(null);
        return userMapper.map(user);
    }

    @QueryMapping
    public UserProfile getUserById(@Argument String userProfileId) {
        UserProfileEntity user = userService.getUserById(userProfileId).orElse(null);
        return userMapper.map(user);
    }

    @MutationMapping
    public UserProfile createEmptyProfileByEmail(@Argument String email) {
        UserProfileEntity user = userService.createEmptyProfileByEmail(email);
        return userMapper.map(user);
    }
}
