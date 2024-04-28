package com.smirnoff.home.ui.service.security;

import java.io.Serializable;

import com.smirnoff.home.ui.adapter.security.UserSessionAdapter;
import com.smirnoff.home.ui.model.security.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class UserSessionServiceImpl implements UserSessionService, Serializable {

    private final UserSessionAdapter userSessionAdapter;

    public UserSessionServiceImpl(UserSessionAdapter userSessionAdapter) {
        this.userSessionAdapter = userSessionAdapter;
    }

    public UserModel getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        return new UserModel(
                principal.getAttribute("given_name"),
                principal.getAttribute("family_name"),
                principal.getAttribute("email"),
                principal.getAttribute("picture")
        );
    }

    @Override
    public void createSession(UserModel userModel) {
        userSessionAdapter.createSession(userModel);
    }

}