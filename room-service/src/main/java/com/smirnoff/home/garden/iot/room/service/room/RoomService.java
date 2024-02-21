package com.smirnoff.home.garden.iot.room.service.room;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<RoomEntity> getAll();

    RoomEntity create(String name);

    Optional<RoomEntity> getRoom(String roomId);
}
