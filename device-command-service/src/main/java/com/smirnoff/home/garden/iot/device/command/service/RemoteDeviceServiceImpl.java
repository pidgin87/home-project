package com.smirnoff.home.garden.iot.device.command.service;

import com.smirnoff.home.garden.iot.device.command.adapter.TuyaAdapter;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDevice;
import com.smirnoff.home.garden.iot.device.command.model.RemoteDeviceStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RemoteDeviceServiceImpl implements RemoteDeviceService {
    private final TuyaAdapter tuyaAdapter;

    @Override
    public RemoteDevice getDevice(String id) {
        return tuyaAdapter.getDeviceDetails(id);
    }

    @Override
    public List<RemoteDevice> getDevices(List<String> ids) {
        List<RemoteDevice> devices = new ArrayList<>();
        for (String id : ids) {
            RemoteDevice device = getDevice(id);
            device.setStatus(filterPropertyField(device.getStatus()));
            devices.add(device);
        }
        return devices;
    }

    private List<RemoteDeviceStatus> filterPropertyField(List<RemoteDeviceStatus> statusList) {
        List<RemoteDeviceStatus> arrays = new ArrayList<>();

        for (RemoteDeviceStatus status : statusList) {
            RemoteDeviceStatus.RemoteDeviceStatusProperty property = RemoteDeviceStatus.RemoteDeviceStatusProperty.findByCode(status.getCode());
            if (property != RemoteDeviceStatus.RemoteDeviceStatusProperty.UNDEFINED) {
                status.setProperty(property);
                arrays.add(status);
            }
        }

        return arrays;
    }
}
