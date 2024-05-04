package com.smirnoff.home.platform.session.client;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.platform.session.client.model.CreateSession;
import com.smirnoff.home.platform.session.client.model.GetUserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "platform-session-service", path = "/api/platform/session")
public interface SessionGraphQlClient {

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<CreateSession> createSession(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetUserProfile> getUserProfile(@RequestBody GraphQlRequest request);
}
