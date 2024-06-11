package com.smirnoff.home.platform.dictionary.mapper;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.platform.dictionary.persistance.entity.currency.CurrencyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    List<CurrencyModel> map(List<CurrencyEntity> currencies);
}
