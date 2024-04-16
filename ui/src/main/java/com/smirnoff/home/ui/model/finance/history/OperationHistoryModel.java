package com.smirnoff.home.ui.model.finance.history;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.model.finance.fund.FundModel;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OperationHistoryModel {
    private final String id;

    private final ProductModel sourceProduct;
    private final FundModel sourceFund;
    private final CurrencyModel sourceCurrency;
    private final BigDecimal sourceAmount;

    private final ProductModel destinationProduct;
    private final FundModel destinationFund;
    private final CurrencyModel destinationCurrency;
    private final BigDecimal destinationAmount;

    private final String description;
}
