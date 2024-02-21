package com.smirnoff.home.garden.iot.room.service.device;

import com.smirnoff.home.garden.iot.room.model.Device;
import com.smirnoff.home.garden.iot.room.model.Room;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    Map<Room, List<Device>> findDevicesByRoom(List<Room> rooms);
}
