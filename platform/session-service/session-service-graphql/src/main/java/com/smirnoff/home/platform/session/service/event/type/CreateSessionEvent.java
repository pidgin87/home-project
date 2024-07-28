package com.smirnoff.home.platform.session.service.event.type;

import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateSessionEvent {
    private final SessionEntity session;
}
