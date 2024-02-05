package com.smirnoff.home.garden.iot.device.service;

import com.smirnoff.home.garden.iot.device.persistance.model.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();
}
