package com.smirnoff.home.platform.session.mapper;

import com.smirnoff.home.platform.session.model.UserProfileSession;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileSessionMapper {

    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "id", source = "userProfileId")
    UserProfileSession mapOne(UserProfileSessionEntity userProfileSession);
}
