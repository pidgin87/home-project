package com.smirnoff.home.platform.session.service.session.lc;

import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionLifecycleImpl implements SessionLifecycle {
    @Override
    public SessionEntity createNew() {
        return new SessionEntity();
    }

    @Override
    public void beforeSave(SessionEntity session) {
        session.setLastActivity(LocalDateTime.now());
    }
}
