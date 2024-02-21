package com.smirnoff.home.garden.iot.room.mapper;

import com.smirnoff.home.garden.iot.room.model.Room;
import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room mapOne(RoomEntity room);
}
