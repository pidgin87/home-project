package com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.service.mapper;

import com.smirnoff.home.finance.rate.procedure.integration.stockrategrabber.model.FinanceYahooResultMetaDto;
import com.smirnoff.home.finance.rate.procedure.persist.model.StockRateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockRateEntityMapper {

    @Mapping(target = "value", source = "result.regularMarketPrice")
    StockRateEntity map(FinanceYahooResultMetaDto result, String ticker, String dictionaryId);
}
