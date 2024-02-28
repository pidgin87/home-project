package com.smirnoff.home.garden.iot.device.command.adapter;

import com.smirnoff.home.garden.iot.device.command.model.RemoteDevice;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.Path;

public interface TuyaAdapter {
    @GET("/v1.0/devices/{deviceId}")
    RemoteDevice getDeviceDetails(@Path("deviceId") String deviceId);
}
