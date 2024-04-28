package com.smirnoff.home.ui.service.security;

import com.smirnoff.home.ui.model.security.UserModel;

public interface UserSessionService {
    UserModel getUser();

    void createSession(UserModel userModel);
}
