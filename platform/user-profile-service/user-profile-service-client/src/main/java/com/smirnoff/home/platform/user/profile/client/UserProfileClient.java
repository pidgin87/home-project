package com.smirnoff.home.platform.user.profile.client;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.platform.user.profile.client.model.CreateEmptyProfileByEmail;
import com.smirnoff.home.platform.user.profile.client.model.GetUserByEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "platform-user-profile-service", path = "/api/platform/user-profile")
public interface UserProfileClient {
    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetUserByEmail> getUserByEmail(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<CreateEmptyProfileByEmail> createEmptyProfileByEmail(@RequestBody GraphQlRequest request);
}
