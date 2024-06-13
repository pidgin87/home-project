package com.smirnoff.home.ui.model.finance.history;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.finance.product.model.ProductModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OperationHistoryModel {
    private final String id;

    private final ProductModel sourceProduct;
    private final Fund sourceFund;
    private final CurrencyModel sourceCurrency;
    private final BigDecimal sourceAmount;

    private final ProductModel destinationProduct;
    private final Fund destinationFund;
    private final CurrencyModel destinationCurrency;
    private final BigDecimal destinationAmount;

    private final String description;
}
