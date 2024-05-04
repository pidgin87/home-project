package com.smirnoff.home.platform.session.mapper;

import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    UserSession map(SessionEntity session);
}
