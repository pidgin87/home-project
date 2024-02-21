package com.smirnoff.home.garden.iot.device.command.service;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDevice;

import java.util.List;

public interface RemoteDeviceService {
    RemoteDevice getDevice(String id);

    List<RemoteDevice> getDevices(List<String> ids);
}
