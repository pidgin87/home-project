package com.smirnoff.home.ui.adapter.userprofile;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.user.profile.client.UserProfileClient;
import com.smirnoff.home.platform.user.profile.client.model.CreateEmptyProfileByEmail;
import com.smirnoff.home.platform.user.profile.client.model.GetUserByEmail;
import com.smirnoff.home.platform.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileAdapterImpl implements UserProfileAdapter {

    private final UserProfileClient userProfileClient;

    //language=graphql
    private static final String GET_USER_BY_EMAIL = """
            query GetUserByEmail($email: String) {
                getUserByEmail(email: $email) {
                    id
                }
            }
            """;

    //language=graphql
    private static final String CREATE_EMPTY_PROFILE_BY_EMAIL = """
            mutation CreateEmptyProfileByEmail($email: String) {
                createEmptyProfileByEmail(email: $email) {
                    id
                }
            }
            """;

    @Override
    public UserProfile getProfileByEmail(String email) {
        GraphQlResponse<GetUserByEmail> response = userProfileClient.getUserByEmail(
                GraphQlRequest.builder()
                        .query(GET_USER_BY_EMAIL)
                        .operationName("GetUserByEmail")
                        .variables(new GraphQlVariables()
                                .addObject("email", email))
                        .build()
        );
        return response.getData().getUserByEmail();
    }

    @Override
    public UserProfile createEmptyProfileByEmail(String email) {
        GraphQlResponse<CreateEmptyProfileByEmail> response = userProfileClient.createEmptyProfileByEmail(
                GraphQlRequest.builder()
                        .query(CREATE_EMPTY_PROFILE_BY_EMAIL)
                        .operationName("CreateEmptyProfileByEmail")
                        .variables(new GraphQlVariables()
                                .addObject("email", email))
                        .build()
        );
        return response.getData().createEmptyProfileByEmail();
    }
}
