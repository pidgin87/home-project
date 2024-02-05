package com.smirnoff.home.garden.iot.room.service.lc;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomLifecycleImpl implements RoomLifecycle {
    @Override
    public RoomEntity createNew(String name) {
        RoomEntity entity = new RoomEntity();
        entity.setName(name);
        return entity;
    }
}
