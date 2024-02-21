package com.smirnoff.home.garden.iot.room.service.device;

import com.smirnoff.home.garden.iot.room.model.Device;
import com.smirnoff.home.garden.iot.room.model.Room;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final ProducerTemplate producerTemplate;

    @Override
    public Map<Room, List<Device>> findDevicesByRoom(List<Room> rooms) {
        Map<String, Room> roomMap = rooms.stream().collect(Collectors.toMap(Room::getId, Function.identity()));
        List<Device> devices = producerTemplate.requestBody("direct:get-devices-by-rooms", roomMap.keySet(), List.class);

        return devices.stream().collect(
                Collectors.groupingBy(device -> roomMap.get(device.getRoomId()))
        );
    }
}
