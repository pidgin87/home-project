package com.smirnoff.home.ui.adapter.security;

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

    public void createSession(UserModel user) {
    }
}
