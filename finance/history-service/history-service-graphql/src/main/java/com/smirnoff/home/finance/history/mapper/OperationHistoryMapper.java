package com.smirnoff.home.finance.history.mapper;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {DateTimeMapper.class}
)
public interface OperationHistoryMapper {
    OperationHistoryEntity map(String sourceProduct, String sourceFund, BigDecimal sourceAmount, String sourceCurrency,
                               String destinationProduct, String destinationFund, BigDecimal destinationAmount,
                               String destinationCurrency, String description, LocalDateTime createdDate);

    List<OperationHistoryDto> map(List<OperationHistoryEntity> operations);

    OperationHistoryDto map(OperationHistoryEntity operation);

    default ProductModel mapProduct(String productId) {
        return new ProductModel(productId, null, null, null);
    }

    default Fund mapFund(String fundId) {
        return new Fund(fundId, null);
    }

    default CurrencyModel mapCurrency(String currencyId) {
        return new CurrencyModel(currencyId, null, null, null);
    }
}
