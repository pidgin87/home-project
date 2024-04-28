package com.smirnoff.home.platform.extension.graphql.configuration;

import com.smirnoff.home.platform.session.client.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GraphQLSessionHeaderInterceptor implements WebGraphQlInterceptor {

    private final SessionClientService sessionClientService;

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String sessionId = request.getHeaders().getFirst("x-platform-session-id");
        sessionClientService.init(sessionId);
        return chain.next(request);
    }
}

