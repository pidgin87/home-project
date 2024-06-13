package com.smirnoff.home.finance.product.mapper;

import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductModel> map(List<ProductEntity> products);

    ProductModel map(ProductEntity product);
}
