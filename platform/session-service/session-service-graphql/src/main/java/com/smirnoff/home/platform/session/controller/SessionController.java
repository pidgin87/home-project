package com.smirnoff.home.platform.session.controller;

import com.smirnoff.home.platform.session.mapper.SessionMapper;
import com.smirnoff.home.platform.session.mapper.UserProfileSessionMapper;
import com.smirnoff.home.platform.session.model.UserProfileSession;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import com.smirnoff.home.platform.session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final SessionMapper sessionMapper;
    private final UserProfileSessionMapper userProfileSessionMapper;

    @MutationMapping
    public UserSession createSession(@Argument String userProfileId) {
        SessionEntity session = sessionService.createSession(userProfileId);
        return sessionMapper.map(session);
    }

    @QueryMapping
    public UserProfileSession getUserProfile(@Argument String sessionId) {
        UserProfileSessionEntity userProfileSession = sessionService.getUserProfile(sessionId);
        return userProfileSessionMapper.mapOne(userProfileSession);
    }
}
