package com.smirnoff.home.garden.iot.room.contoller;

import com.smirnoff.home.garden.iot.room.mapper.RoomMapper;
import com.smirnoff.home.garden.iot.room.model.Device;
import com.smirnoff.home.garden.iot.room.model.DeviceStatus;
import com.smirnoff.home.garden.iot.room.model.Room;
import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;
import com.smirnoff.home.garden.iot.room.service.device.DeviceService;
import com.smirnoff.home.garden.iot.room.service.room.RoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GetRoomController {
    private final RoomService roomService;
    private final DeviceService deviceService;
    private final RoomMapper roomMapper;

    @QueryMapping
    public Room getRoom(@Argument String roomId) {
        Optional<RoomEntity> room = roomService.getRoom(roomId);
        return room.map(roomMapper::mapOne).orElse(null);
    }

    @BatchMapping(typeName = "RoomDetail", field = "devices")
    public Map<Room, List<Device>> devices(List<Room> rooms) {
        return deviceService.findDevicesByRoom(rooms);
    }

    @BatchMapping(typeName = "DeviceDetail", field = "statuses")
    public Map<Device, List<DeviceStatus>> statuses(List<Device> devices) {
        return Collections.emptyMap();
    }
}
