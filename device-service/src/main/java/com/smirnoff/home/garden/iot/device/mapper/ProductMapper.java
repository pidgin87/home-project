package com.smirnoff.home.garden.iot.device.mapper;

import com.smirnoff.home.garden.iot.device.model.Product;
import com.smirnoff.home.garden.iot.device.persistance.model.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<Product> map(List<ProductEntity> products);

    Product map(ProductEntity products);
}
