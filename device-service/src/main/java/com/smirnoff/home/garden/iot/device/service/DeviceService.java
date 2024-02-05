package com.smirnoff.home.garden.iot.device.service;

import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.model.DeviceStatus;
import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    List<DeviceEntity> getAll();

    Optional<DeviceEntity> findById(String id);

    List<DeviceEntity> getAll(String roomId);

    List<DeviceStatus> getStatuses(Device device);
}
