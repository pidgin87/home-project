package com.smirnoff.home.platform.session.client.service;

import com.smirnoff.home.platform.session.model.UserProfileSession;

public interface SessionClientService {
    String getCompanyId();

    UserProfileSession getUserProfile();

    String getSessionId();

    void setSessionId(String id);
}
