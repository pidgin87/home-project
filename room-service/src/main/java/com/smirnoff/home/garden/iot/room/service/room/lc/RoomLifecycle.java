package com.smirnoff.home.garden.iot.room.service.room.lc;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;

public interface RoomLifecycle {
    RoomEntity createNew(String name);
}
