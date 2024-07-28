package com.smirnoff.home.platform.user.profile.mapper;

import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    UserProfile map(UserProfileEntity user);
}
