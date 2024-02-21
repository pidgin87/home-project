package com.smirnoff.home.garden.iot.device.contoller;

import com.smirnoff.home.garden.iot.device.mapper.DeviceMapper;
import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import com.smirnoff.home.garden.iot.device.service.DeviceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GetDevicesByRoomsController {
    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;

    @QueryMapping
    public List<Device> getDevicesByRooms(@Argument List<String> roomIds) {
        List<DeviceEntity> devices = deviceService.getAll(roomIds);
        return deviceMapper.map(devices);
    }
}
