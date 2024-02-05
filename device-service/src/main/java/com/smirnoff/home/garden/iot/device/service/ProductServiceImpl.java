package com.smirnoff.home.garden.iot.device.service;

import com.smirnoff.home.garden.iot.device.persistance.model.ProductEntity;
import com.smirnoff.home.garden.iot.device.persistance.repository.ProductEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductEntityRepository productEntityRepository;
    @Override
    public List<ProductEntity> findAll() {
        return productEntityRepository.findAll();
    }
}
