package com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.model;

import lombok.Data;

import java.util.List;

@Data
public class FinanceYahooChartDto {
    private List<FinanceYahooResultDto> result;
}
