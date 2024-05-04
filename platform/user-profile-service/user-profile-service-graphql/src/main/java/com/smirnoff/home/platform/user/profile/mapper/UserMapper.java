package com.smirnoff.home.platform.user.profile.mapper;

import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfile map(UserProfileEntity user);
}
