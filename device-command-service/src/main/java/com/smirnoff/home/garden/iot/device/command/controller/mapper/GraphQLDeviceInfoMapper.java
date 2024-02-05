package com.smirnoff.home.garden.iot.device.command.controller.mapper;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceInfo;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceStatus;
import com.smirnoff.home.garden.iot.device.command.service.RemoteDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLDeviceInfoMapper {
    @SchemaMapping(typeName = "RemoteDeviceInfo")
    public List<RemoteDeviceStatus> status(RemoteDeviceInfo remoteDeviceInfo) {
        return remoteDeviceInfo.getStatus();
    }
}
