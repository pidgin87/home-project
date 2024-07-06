package com.smirnoff.home.finance.history.mapper;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;
import com.smirnoff.home.finance.history.persistance.repository.projections.ProductBalanceProjection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BalanceProductMapper {
    List<ProductBalanceDto> map(List<ProductBalanceProjection> balances);
}
