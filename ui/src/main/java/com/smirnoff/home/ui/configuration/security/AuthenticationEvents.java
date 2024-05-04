package com.smirnoff.home.ui.configuration.security;

import com.smirnoff.home.platform.session.client.service.SessionClientService;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.ui.service.session.UserSessionService;
import com.smirnoff.home.ui.service.userprofile.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationEvents {

    private final UserSessionService userSessionService;
    private final UserProfileService userProfileService;
    private final SessionClientService sessionClientService;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        String email = principal.getAttribute("email");
        UserProfile profile = userProfileService.getProfileByEmail(email);
        UserSession session = userSessionService.createSession(profile);

        sessionClientService.setSessionId(session.id());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
    }
}