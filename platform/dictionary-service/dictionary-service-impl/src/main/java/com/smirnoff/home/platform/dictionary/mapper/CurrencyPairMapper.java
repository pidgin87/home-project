package com.smirnoff.home.platform.dictionary.mapper;

import com.smirnoff.home.platform.dictionary.dto.currencypair.CurrencyPairModel;
import com.smirnoff.home.platform.dictionary.persistance.entity.currencypair.CurrencyPairEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyPairMapper {
    List<CurrencyPairModel> map(List<CurrencyPairEntity> currencies);
}
