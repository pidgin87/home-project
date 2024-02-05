package com.smirnoff.home.garden.iot.device.model;

import com.smirnoff.home.garden.iot.device.persistance.model.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;
    private String uid;
    private String name;
    private ProductType type;
}
