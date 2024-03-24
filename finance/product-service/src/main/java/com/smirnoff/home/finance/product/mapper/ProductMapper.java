package com.smirnoff.home.finance.product.mapper;

import com.smirnoff.home.finance.product.model.ProductDto;
import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductDto> map(List<ProductEntity> funds);

    ProductDto map(ProductEntity fund);
}
