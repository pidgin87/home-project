package com.smirnoff.home.ui.service.session;

import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.ui.model.security.UserModel;

public interface UserSessionService {
    UserModel getUser();

    UserSession createSession(UserProfile userProfile);
}
