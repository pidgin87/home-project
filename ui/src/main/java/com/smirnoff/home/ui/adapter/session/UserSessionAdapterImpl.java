package com.smirnoff.home.ui.adapter.session;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.session.client.SessionGraphQlClient;
import com.smirnoff.home.platform.session.client.model.CreateSession;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSessionAdapterImpl implements UserSessionAdapter {

    //language=graphql
    private static final String CREATE_SESSION_REQUEST = """
            mutation CreateSession($userProfileId: String) {
                createSession(userProfileId: $userProfileId) {
                    id
                }
            }
            """;

    private final SessionGraphQlClient sessionGraphQlClient;

    @Override
    public UserSession createSession(UserProfile userProfile) {
        GraphQlResponse<CreateSession> response = sessionGraphQlClient.createSession(
                GraphQlRequest.builder()
                        .query(CREATE_SESSION_REQUEST)
                        .operationName("CreateSession")
                        .variables(new GraphQlVariables()
                                .addObject("userProfileId", userProfile.getId()))
                        .build()
        );
        return response.getData().createSession();
    }
}
