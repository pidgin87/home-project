package com.smirnoff.home.garden.iot.device.command.service;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceInfo;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceStatus;

import java.util.List;

public interface RemoteDeviceService {
    RemoteDeviceInfo getDeviceInfo(String remoteUid);

    List<RemoteDeviceStatus> getStatus(String remoteUid);
}
