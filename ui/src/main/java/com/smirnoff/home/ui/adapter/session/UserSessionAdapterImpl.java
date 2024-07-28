package com.smirnoff.home.ui.adapter.session;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.session.client.SessionGraphQlClient;
import com.smirnoff.home.platform.session.client.model.CreateSession;
import com.smirnoff.home.platform.session.model.UserRoleDto;
import com.smirnoff.home.platform.session.model.UserSession;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSessionAdapterImpl implements UserSessionAdapter {

    //language=graphql
    private static final String REQUESTS = """
            mutation CreateSession($userProfileId: String) {
                createSession(userProfileId: $userProfileId) {
                    id
                }
            }

            query getUserRoles($sessionId: String) {
                getUserRoles(sessionId: $sessionId)
            }
            """;

    private final SessionGraphQlClient sessionGraphQlClient;

    @Override
    public UserSession createSession(UserProfile userProfile) {
        GraphQlResponse<CreateSession> response = sessionGraphQlClient.createSession(
                GraphQlRequest.builder()
                        .query(REQUESTS)
                        .operationName("CreateSession")
                        .variables(new GraphQlVariables()
                                .addObject("userProfileId", userProfile.getId()))
                        .build()
        );
        return response.getData().createSession();
    }

    @Override
    public List<UserRoleDto> getRoles(String sessionId) {
        GraphQlResponse<List<UserRoleDto>> response = sessionGraphQlClient.getRoles(
                GraphQlRequest.builder()
                        .query(REQUESTS)
                        .operationName("getRoles")
                        .variables(new GraphQlVariables()
                                .addObject("sessionId", sessionId))
                        .build()
        );

        return response.getData();
    }
}
