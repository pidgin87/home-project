package com.smirnoff.home.platform.session.client.service;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.session.SessionConstant;
import com.smirnoff.home.platform.session.client.SessionGraphQlClient;
import com.smirnoff.home.platform.session.client.model.GetUserProfile;
import com.smirnoff.home.platform.session.model.UserProfileSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.smirnoff.home.platform.session.SessionConstant.SESSION_ATTRIBUTE;

@Component
@RequiredArgsConstructor
public class SessionClientServiceImpl implements SessionClientService {
    private final SessionGraphQlClient sessionGraphQlClient;

    //language=graphql
    private static final String GET_USER_PROFILE_REQUEST = """
            query GetUserProfile($sessionId: String) {
                getUserProfile(sessionId: $sessionId) {
                    id
                    company {
                        id                  
                    }
                }
            }
            """;

    @Override
    public String getCompanyId() {
        return getUserProfile().getCompany().getId();
    }

    @Override
    public UserProfileSession getUserProfile() {
        GraphQlResponse<GetUserProfile> userProfile = sessionGraphQlClient.getUserProfile(
                GraphQlRequest.builder()
                        .query(GET_USER_PROFILE_REQUEST)
                        .operationName("GetUserProfile")
                        .variables(new GraphQlVariables()
                                .addObject("sessionId", getSessionId()))
                        .build()
        );
        return userProfile.getData().getUserProfile();
    }

    @Override
    public String getSessionId() {
        return (String) getSession().getAttribute(SESSION_ATTRIBUTE);
    }

    @Override
    public void setSessionId(String sessionId) {
        getSession().setAttribute(SessionConstant.SESSION_ATTRIBUTE, sessionId);
    }

    private static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }
}
