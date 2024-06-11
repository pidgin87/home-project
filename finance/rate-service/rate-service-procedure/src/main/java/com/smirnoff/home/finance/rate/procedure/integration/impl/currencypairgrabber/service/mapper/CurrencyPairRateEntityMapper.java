package com.smirnoff.home.finance.rate.procedure.integration.impl.currencypairgrabber.service.mapper;

import com.smirnoff.home.finance.rate.procedure.integration.model.yahoofinance.FinanceYahooResultMetaDto;
import com.smirnoff.home.finance.rate.procedure.persist.model.CurrencyPairRateEntity;
import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyPairRateEntityMapper {

    @Mapping(target = "value", source = "result.regularMarketPrice")
    CurrencyPairRateEntity map(FinanceYahooResultMetaDto result,
                               String ticker,
                               String dictionaryId);
}
