package com.smirnoff.home.ui.model.finance.history;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OperationHistoryModel {
    private final String id;

    private final ProductModel sourceProduct;
    private final CurrencyModel sourceCurrency;
    private final BigDecimal sourceAmount;

    private final String description;
}
