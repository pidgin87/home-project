package com.smirnoff.home.finance.product.mapper;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;
import com.smirnoff.home.finance.product.model.AmountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(target = "amount", source = "amount")
    AmountModel map(ProductBalanceDto balanceByProduct);
}
