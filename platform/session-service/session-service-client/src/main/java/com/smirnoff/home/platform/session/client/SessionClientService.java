package com.smirnoff.home.platform.session.client;

public interface SessionClientService {
    String getCompanyId();

    void init(String sessionId);

    String getSessionId();
}
