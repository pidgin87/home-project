package com.smirnoff.home.garden.iot.device.command.controller.mapper;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDevice;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceStatus;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLDeviceInfoMapper {
    @SchemaMapping(typeName = "RemoteDeviceInfo")
    public List<RemoteDeviceStatus> status(RemoteDevice remoteDeviceInfo) {
        return remoteDeviceInfo.getStatus();
    }
}
