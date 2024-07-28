package com.smirnoff.home.platform.session.service.session;

import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserRoleEntity;

import java.util.List;

public interface SessionService {
    SessionEntity createSession(String userProfileId);

    UserProfileSessionEntity getUserProfile(String userSessionId);

    List<UserRoleEntity> getUserRoles(String userSessionId);
}
