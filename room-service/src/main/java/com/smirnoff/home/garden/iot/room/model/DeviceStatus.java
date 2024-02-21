package com.smirnoff.home.garden.iot.room.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceStatus {
    private String deviceId;
    private String code;
    private String value;
}
