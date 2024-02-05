package com.smirnoff.home.garden.iot.room.service;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;

import java.util.List;

public interface RoomService {
    List<RoomEntity> getAll();

    RoomEntity create(String name);
}
