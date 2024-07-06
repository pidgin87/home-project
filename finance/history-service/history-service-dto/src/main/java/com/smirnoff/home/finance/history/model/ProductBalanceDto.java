package com.smirnoff.home.finance.history.model;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductBalanceDto(String productId,
                                BigDecimal amount,
                                CurrencyModel currency) {
}
