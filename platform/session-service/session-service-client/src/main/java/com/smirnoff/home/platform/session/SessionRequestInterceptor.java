package com.smirnoff.home.platform.session;

import com.smirnoff.home.platform.session.client.service.SessionClientService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionRequestInterceptor implements RequestInterceptor {
    private final SessionClientService sessionClientService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("x-platform-session-id", sessionClientService.getSessionId());
    }
}
