package com.smirnoff.home.platform.session.mapper;

import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "userProfileId", source = "id")
    @Mapping(target = "id", ignore = true)
    UserProfileSessionEntity mapOne(UserProfile userProfile);
}
