package com.smirnoff.home.platform.dictionary.mapper;

import com.smirnoff.home.platform.dictionary.dto.stock.StockModel;
import com.smirnoff.home.platform.dictionary.persistance.entity.StockEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    List<StockModel> mapList(List<StockEntity> stocks);
}
