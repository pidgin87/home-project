package com.smirnoff.home.ui.adapter.session;

import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.ui.model.security.UserModel;

public interface UserSessionAdapter {
    void createSession(UserProfile userProfile);
}
