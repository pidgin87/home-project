package com.smirnoff.home.platform.session.controller;

import com.smirnoff.home.platform.session.mapper.SessionMapper;
import com.smirnoff.home.platform.session.mapper.UserProfileSessionMapper;
import com.smirnoff.home.platform.session.mapper.UserRoleMapper;
import com.smirnoff.home.platform.session.model.UserProfileSession;
import com.smirnoff.home.platform.session.model.UserRoleDto;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserRoleEntity;
import com.smirnoff.home.platform.session.service.session.SessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final SessionMapper sessionMapper;
    private final UserProfileSessionMapper userProfileSessionMapper;
    private final UserRoleMapper userRoleMapper;

    @MutationMapping
    @Transactional
    public UserSession createSession(@Argument String userProfileId) {
        return sessionMapper.map(
                sessionService.createSession(userProfileId)
        );
    }

    @QueryMapping
    public UserProfileSession getUserProfile(@Argument String sessionId) {
        UserProfileSessionEntity userProfileSession = sessionService.getUserProfile(sessionId);
        return userProfileSessionMapper.mapOne(userProfileSession);
    }

    @QueryMapping
    public List<UserRoleDto> getUserRoles(@Argument String sessionId) {
        List<UserRoleEntity> roles = sessionService.getUserRoles(sessionId);
        return userRoleMapper.map(roles);
    }
}
