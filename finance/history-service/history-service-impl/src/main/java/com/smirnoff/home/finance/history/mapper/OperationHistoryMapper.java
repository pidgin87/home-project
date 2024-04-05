package com.smirnoff.home.finance.history.mapper;

import com.smirnoff.home.finance.history.model.CurrencyDto;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.model.ProductDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationHistoryMapper {
    OperationHistoryEntity map(String sourceProduct, BigDecimal sourceAmount, String sourceCurrency);

    List<OperationHistoryDto> map(List<OperationHistoryEntity> operations);

    OperationHistoryDto map(OperationHistoryEntity operation);

    default ProductDto mapProduct(String productId) {
        return new ProductDto(productId, null, null);
    }

    default CurrencyDto mapCurrency(String currencyId) {
        return new CurrencyDto(currencyId, null, null);
    }
}
