package com.smirnoff.home.garden.iot.device.contoller.mapper;

import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.model.DeviceStatus;
import com.smirnoff.home.garden.iot.device.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLDeviceInfoMapper {
    private final DeviceService deviceService;

    @SchemaMapping(typeName = "DeviceInfo")
    public List<DeviceStatus> statuses(Device device) {
        return deviceService.getStatuses(device);
    }
}
