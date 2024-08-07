package com.smirnoff.home.ui.service.session;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.smirnoff.home.platform.session.client.service.SessionClientService;
import com.smirnoff.home.platform.session.model.UserRoleDto;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.ui.adapter.session.UserSessionAdapter;
import com.smirnoff.home.ui.model.security.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@RequiredArgsConstructor
public class UserSessionServiceImpl implements UserSessionService, Serializable {

    private final UserSessionAdapter userSessionAdapter;
    private final SessionClientService sessionClientService;

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
    public UserSession createSession(UserProfile userProfile) {
        Objects.requireNonNull(userProfile);
        return userSessionAdapter.createSession(userProfile);
    }

    @Override
    public List<UserRoleDto> getRoles() {
        return userSessionAdapter.getRoles(sessionClientService.getSessionId());
    }
}