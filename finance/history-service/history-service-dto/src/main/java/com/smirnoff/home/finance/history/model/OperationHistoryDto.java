package com.smirnoff.home.finance.history.model;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
public class OperationHistoryDto {
    private String id;

    private ProductDto sourceProduct;
    private BigDecimal sourceAmount;
    private CurrencyModel sourceCurrency;

    private ProductDto destinationProduct;
    private BigDecimal destinationAmount;
    private CurrencyModel destinationCurrency;

    private String description;

    private OffsetDateTime createdDate;
}
