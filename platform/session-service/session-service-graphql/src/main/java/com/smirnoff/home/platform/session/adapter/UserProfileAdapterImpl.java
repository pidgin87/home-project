package com.smirnoff.home.platform.session.adapter;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.user.profile.client.UserProfileClient;
import com.smirnoff.home.platform.user.profile.client.model.GetUserById;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileAdapterImpl implements UserProfileAdapter {
    private final UserProfileClient userProfileClient;

    //language=graphql
    private static final String GET_USER_BY_ID = """
            query GetUserById($userProfileId: String) {
                getUserById(userProfileId: $userProfileId) {
                    id
                    company {
                        id
                    }
                    roles
                }
            }
            """;

    @Override
    public UserProfile getUserProfile(String userProfileId) {
        GraphQlResponse<GetUserById> response = userProfileClient.getUserById(
                GraphQlRequest.builder()
                        .query(GET_USER_BY_ID)
                        .operationName("GetUserById")
                        .variables(new GraphQlVariables()
                                .addObject("userProfileId", userProfileId))
                        .build()
        );
        return response.getData().getUserById();
    }
}
