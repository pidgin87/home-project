package com.smirnoff.home.garden.iot.device.command.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RemoteDevice {
    private String id;
    private String name;
    private Boolean online;
    private String product_id;
    private String product_name;
    private List<RemoteDeviceStatus> status;
}
