package com.smirnoff.home.garden.iot.device.command.service;

import com.smirnoff.home.garden.iot.device.command.adapter.TuyaAdapter;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceInfo;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RemoteDeviceServiceImpl implements RemoteDeviceService {
    private final TuyaAdapter tuyaAdapter;
    @Override
    public RemoteDeviceInfo getDeviceInfo(String remoteUid) {
        return tuyaAdapter.getDeviceDetails(remoteUid);
    }

    @Override
    public List<RemoteDeviceStatus> getStatus(String remoteUid) {
        return null;
    }
}
