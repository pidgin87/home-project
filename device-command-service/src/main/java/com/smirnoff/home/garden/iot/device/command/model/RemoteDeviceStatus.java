package com.smirnoff.home.garden.iot.device.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteDeviceStatus {
    private String code;
    private Object value;
}
