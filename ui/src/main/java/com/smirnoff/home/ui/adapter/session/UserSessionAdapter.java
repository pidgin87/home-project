package com.smirnoff.home.ui.adapter.session;

import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;

public interface UserSessionAdapter {
    UserSession createSession(UserProfile userProfile);
}
