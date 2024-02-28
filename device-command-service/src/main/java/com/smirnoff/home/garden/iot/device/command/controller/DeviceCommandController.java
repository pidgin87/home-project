package com.smirnoff.home.garden.iot.device.command.controller;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDevice;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceStatus;
import com.smirnoff.home.garden.iot.device.command.service.RemoteDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DeviceCommandController {

    private final RemoteDeviceService remoteDeviceService;

    @QueryMapping
    public RemoteDevice getRemoteDevice(@Argument String id) {
        return remoteDeviceService.getDevice(id);
    }

    @QueryMapping
    public List<RemoteDevice> getRemoteDevices(@Argument List<String> ids) {
        return remoteDeviceService.getDevices(ids);
    }

    @BatchMapping(typeName = "RemoteDevice", field = "statuses")
    public Map<RemoteDevice, List<RemoteDeviceStatus>> statuses(List<RemoteDevice> devices) {
        Map<RemoteDevice, List<RemoteDeviceStatus>> deviceMap = new HashMap<>();
        for (RemoteDevice device : devices) {
            deviceMap.put(device, device.getStatus());
        }
        return deviceMap;
    }
}
