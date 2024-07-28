package com.smirnoff.home.platform.session.service.session;

import com.smirnoff.home.platform.session.adapter.UserProfileAdapter;
import com.smirnoff.home.platform.session.mapper.UserProfileMapper;
import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserRoleEntity;
import com.smirnoff.home.platform.session.persistance.repository.SessionRepository;
import com.smirnoff.home.platform.session.persistance.repository.UserRoleRepository;
import com.smirnoff.home.platform.session.service.event.type.CreateSessionEvent;
import com.smirnoff.home.platform.session.service.session.lc.SessionLifecycle;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionLifecycle sessionLifecycle;
    private final SessionRepository sessionRepository;
    private final UserProfileAdapter userProfileAdapter;
    private final UserProfileMapper userProfileMapper;
    private final UserRoleRepository userRoleRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public SessionEntity createSession(String userProfileId) {
        SessionEntity session = sessionLifecycle.createNew();

        UserProfile userProfile = userProfileAdapter.getUserProfile(userProfileId);
        session.setUserProfileSession(userProfileMapper.mapOne(userProfile));

        sessionLifecycle.beforeSave(session);

        SessionEntity sessionEntity = sessionRepository.saveAndFlush(session);
        applicationEventPublisher.publishEvent(new CreateSessionEvent(sessionEntity));
        return sessionEntity;
    }

    @Override
    public UserProfileSessionEntity getUserProfile(String userSessionId) {
        SessionEntity session = sessionRepository.findById(userSessionId).orElseThrow();
        return session.getUserProfileSession();
    }

    @Override
    public List<UserRoleEntity> getUserRoles(String userSessionId) {
        return userRoleRepository.findBySessionId(userSessionId);
    }
}
