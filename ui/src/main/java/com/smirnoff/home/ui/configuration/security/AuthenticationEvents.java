package com.smirnoff.home.ui.configuration.security;

import com.smirnoff.home.ui.model.security.UserModel;
import com.smirnoff.home.ui.service.security.UserSessionService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    private final UserSessionService userSessionService;

    public AuthenticationEvents(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        String givenName = principal.getAttribute("given_name");
        String lastName = principal.getAttribute("family_name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        userSessionService.createSession(new UserModel(givenName, lastName, email, picture));
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
    }
}