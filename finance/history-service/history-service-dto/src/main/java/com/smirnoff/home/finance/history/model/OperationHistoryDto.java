package com.smirnoff.home.finance.history.model;

import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class OperationHistoryDto {
    private String id;
    private String description;

    private ProductModel sourceProduct;
    private BigDecimal sourceAmount;
    private CurrencyModel sourceCurrency;

    private ProductModel destinationProduct;
    private BigDecimal destinationAmount;
    private CurrencyModel destinationCurrency;

    private OffsetDateTime createdDate;

    public boolean isSourceIsNotNull() {
        return sourceProduct != null && sourceProduct.isNotNull();
    }

    public boolean isDestinationIsNotNull() {
        return destinationProduct != null && destinationProduct.isNotNull();
    }
}
