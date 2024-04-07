package com.smirnoff.home.ui.service.finance.history;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Service
public class MoneyTranslator {

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

    public String toString(BigDecimal amount, CurrencyModel currency) {
        if (amount != null && currency != null) {
            return currency.getSymbol() + " " + decimalFormat.format(amount);
        }
        return null;
    }
}
