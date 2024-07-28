package com.smirnoff.home.platform.user.profile.controller;

import com.smirnoff.home.platform.user.profile.mapper.UserMapper;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.platform.user.profile.model.UserRoleDto;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserRole;
import com.smirnoff.home.platform.user.profile.service.role.RoleService;
import com.smirnoff.home.platform.user.profile.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
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

    @BatchMapping(typeName = "UserProfile", field = "roles")
    public Map<UserProfile, List<UserRoleDto>> sourceProduct(List<UserProfile> profiles) {
        List<String> profileIds = profiles.stream().map(UserProfile::getId).toList();
        Map<String, List<UserRole>> rolesByProfileIds = roleService.getRolesByProfileIds(profileIds);
        Map<UserProfile, List<UserRoleDto>> userRolesByProfileIds = new HashMap<>();
        for (UserProfile profile : profiles) {
            List<UserRoleDto> roles = rolesByProfileIds.get(profile.getId()).stream()
                    .map(role -> UserRoleDto.valueOf(role.name()))
                    .toList();
            userRolesByProfileIds.put(profile, roles);
        }
        return userRolesByProfileIds;
    }

    @MutationMapping
    public UserProfile createEmptyProfileByEmail(@Argument String email) {
        UserProfileEntity user = userService.createEmptyProfileByEmail(email);
        return userMapper.map(user);
    }
}
