package com.smirnoff.home.platform.session.service.session.lc;

import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;

public interface SessionLifecycle {
    SessionEntity createNew();

    void beforeSave(SessionEntity session);
}
