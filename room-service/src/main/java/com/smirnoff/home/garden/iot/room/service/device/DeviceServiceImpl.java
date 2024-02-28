package com.smirnoff.home.garden.iot.room.service.device;

import com.smirnoff.home.garden.iot.room.model.Device;
import com.smirnoff.home.garden.iot.room.model.DeviceStatus;
import com.smirnoff.home.garden.iot.room.model.Room;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.smirnoff.home.garden.iot.room.camel.GetDeviceStatusesRoute.GET_STATUSES_BY_DEVICES;
import static com.smirnoff.home.garden.iot.room.camel.GetDevicesByRoomRoute.GET_DEVICES_BY_ROOMS;

@Component
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final ProducerTemplate producerTemplate;

    @Override
    public Map<Room, List<Device>> findDevicesByRoom(List<Room> rooms) {
        Map<String, Room> roomMap = rooms.stream().collect(Collectors.toMap(Room::getId, Function.identity()));
        List<Device> devices = producerTemplate.requestBody(GET_DEVICES_BY_ROOMS, roomMap.keySet(), List.class);

        return devices.stream().collect(
                Collectors.groupingBy(device -> roomMap.get(device.getRoomId()))
        );
    }

    @Override
    public Map<Device, List<DeviceStatus>> findDeviceStatusesByRoom(List<Device> devices) {
        Map<String, Device> deviceMap = devices.stream().collect(Collectors.toMap(Device::getId, Function.identity()));
        List<DeviceStatus> statuses = producerTemplate.requestBody(GET_STATUSES_BY_DEVICES, deviceMap.keySet(), List.class);

        return statuses.stream().collect(
                Collectors.groupingBy(status -> deviceMap.get(status.getDeviceId()))
        );
    }
}
