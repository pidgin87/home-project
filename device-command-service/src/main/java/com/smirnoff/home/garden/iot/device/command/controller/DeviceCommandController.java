package com.smirnoff.home.garden.iot.device.command.controller;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceInfo;
import com.smirnoff.home.garden.iot.device.command.service.RemoteDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class DeviceCommandController {

    private final RemoteDeviceService remoteDeviceService;

    @QueryMapping
    public RemoteDeviceInfo getRemoteDeviceInfo(@Argument String remoteUid) {
        return remoteDeviceService.getDeviceInfo(remoteUid);
    }
}
