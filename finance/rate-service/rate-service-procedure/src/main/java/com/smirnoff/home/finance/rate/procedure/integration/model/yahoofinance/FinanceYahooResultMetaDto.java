package com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinanceYahooResultMetaDto {
    private String currency;
    private String symbol;
    private BigDecimal regularMarketPrice;
    private BigDecimal previousClose;
}
