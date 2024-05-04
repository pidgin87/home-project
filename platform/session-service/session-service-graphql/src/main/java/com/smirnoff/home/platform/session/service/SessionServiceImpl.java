package com.smirnoff.home.platform.session.service;

import com.smirnoff.home.platform.session.adapter.UserProfileAdapter;
import com.smirnoff.home.platform.session.mapper.UserProfileMapper;
import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import com.smirnoff.home.platform.session.persistance.repository.SessionRepository;
import com.smirnoff.home.platform.session.service.lc.SessionLifecycle;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionLifecycle sessionLifecycle;
    private final SessionRepository sessionRepository;
    private final UserProfileAdapter userProfileAdapter;
    private final UserProfileMapper userProfileMapper;

    @Override
    public SessionEntity createSession(String userProfileId) {
        SessionEntity session = sessionLifecycle.createNew();

        UserProfile userProfile = userProfileAdapter.getUserProfile(userProfileId);
        session.setUserProfileSession(userProfileMapper.mapOne(userProfile));

        sessionLifecycle.beforeSave(session);
        return sessionRepository.saveAndFlush(session);
    }

    @Override
    public UserProfileSessionEntity getUserProfile(String userSessionId) {
        SessionEntity session = sessionRepository.findById(userSessionId).orElseThrow();
        return session.getUserProfileSession();
    }
}
