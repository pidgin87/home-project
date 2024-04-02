package com.smirnoff.home.finance.history.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OperationHistoryDto {
    private String sourceProduct;
    private BigDecimal sourceAmount;
    private String sourceCurrency;
    private String destinationProduct;
    private BigDecimal destinationAmount;
    private String destinationCurrency;
    private LocalDateTime operationDate;
}
