package com.smirnoff.home.garden.iot.device.model;

import com.smirnoff.home.garden.iot.device.persistance.model.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Device {
    private String id;
    private String name;
    private String roomId;
    private String globalId;
    private ProductType type;
    private List<DeviceStatus> statuses;
}
