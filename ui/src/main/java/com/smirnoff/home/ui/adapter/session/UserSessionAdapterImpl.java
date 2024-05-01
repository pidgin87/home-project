package com.smirnoff.home.ui.adapter.session;

import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.ui.model.security.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSessionAdapterImpl implements UserSessionAdapter {

    //language=graphql
//    private static final String CREATE_SESSION_REQUEST = """
//            query CreateSession($email, $givenName, $lastName, $picture) {
//                createSession(email, givenName, lastName, picture) {
//                    id
//                }
//            }
//            """;

    @Override
    public void createSession(UserProfile userProfile) {
        
    }
}
