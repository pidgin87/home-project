package com.smirnoff.home.platform.session.service;

import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import com.smirnoff.home.platform.session.persistance.entity.UserProfileSessionEntity;

public interface SessionService {
    SessionEntity createSession(String userProfileId);

    UserProfileSessionEntity getUserProfile(String userSessionId);
}
