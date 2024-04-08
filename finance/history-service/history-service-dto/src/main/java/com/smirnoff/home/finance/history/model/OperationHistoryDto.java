package com.smirnoff.home.finance.history.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OperationHistoryDto {
    private String id;

    private ProductDto sourceProduct;
    private BigDecimal sourceAmount;
    private CurrencyDto sourceCurrency;

    private ProductDto destinationProduct;
    private BigDecimal destinationAmount;
    private CurrencyDto destinationCurrency;

    private String description;

    private LocalDateTime operationDate;
}
