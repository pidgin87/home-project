package com.smirnoff.home.garden.iot.device.mapper;

import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    List<Device> map(List<DeviceEntity> devices);

    Device map(DeviceEntity device);
}
