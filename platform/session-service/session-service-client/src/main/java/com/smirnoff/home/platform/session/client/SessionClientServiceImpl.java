package com.smirnoff.home.platform.session.client;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SessionClientServiceImpl implements SessionClientService {
    private String sessionId;

    @Override
    public String getCompanyId() {
        return sessionId;
    }

    @Override
    public void init(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }
}
