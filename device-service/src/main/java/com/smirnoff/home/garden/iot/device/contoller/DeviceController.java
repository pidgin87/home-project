package com.smirnoff.home.garden.iot.device.contoller;

import com.smirnoff.home.garden.iot.device.mapper.DeviceMapper;
import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.model.DeviceStatus;
import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import com.smirnoff.home.garden.iot.device.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;

    @QueryMapping
    public List<Device> getDevices() {
        List<DeviceEntity> devices = deviceService.getAll();
        return deviceMapper.map(devices);
    }

    @QueryMapping
    public List<Device> getDeviceList(@Argument List<String> deviceIds) {
        List<DeviceEntity> devices = deviceService.findAll(deviceIds);
        return deviceMapper.map(devices);
    }

    @QueryMapping
    public List<Device> getDevicesByRoom(@Argument String roomId) {
        List<DeviceEntity> devices = deviceService.getAll(roomId);
        return deviceMapper.map(devices);
    }

    @QueryMapping
    public Device getDevice(@Argument String deviceId) {
        Optional<DeviceEntity> device = deviceService.findById(deviceId);
        return deviceMapper.map(device.orElse(null));
    }

    @BatchMapping(typeName = "Device", field = "statuses")
    public Map<Device, List<DeviceStatus>> statuses(List<Device> devices) {
        return deviceService.findStatuses(devices);
    }
}
