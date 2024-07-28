package com.smirnoff.home.ui.adapter.session;

import com.smirnoff.home.platform.session.model.UserRoleDto;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;

import java.util.List;

public interface UserSessionAdapter {
    UserSession createSession(UserProfile userProfile);

    List<UserRoleDto> getRoles(String sessionId);
}
