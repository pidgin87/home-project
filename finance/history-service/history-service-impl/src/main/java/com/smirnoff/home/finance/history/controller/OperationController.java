package com.smirnoff.home.finance.history.controller;

import com.smirnoff.home.finance.history.mapper.OperationHistoryMapper;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.model.VoidResponse;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.service.currency.CurrencyExternalService;
import com.smirnoff.home.finance.history.service.operation.OperationHistoryService;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.isNull;

@Controller
@AllArgsConstructor
public class OperationController {

    private final OperationHistoryService operationHistoryService;
    private final OperationHistoryMapper operationHistoryMapper;
    private final CurrencyExternalService currencyExternalService;

    @QueryMapping
    public List<OperationHistoryDto> getOperationList() {
        List<OperationHistoryEntity> operations = operationHistoryService.getAll();
        return operationHistoryMapper.map(operations);
    }

    @BatchMapping(typeName = "Operation", field = "sourceCurrency")
    public Map<OperationHistoryDto, CurrencyModel> sourceCurrency(List<OperationHistoryDto> operations) {
        return getOperationsWithCurrency(operations, OperationHistoryDto::getSourceCurrency);
    }

    @BatchMapping(typeName = "Operation", field = "destinationCurrency")
    public Map<OperationHistoryDto, CurrencyModel> destinationCurrency(List<OperationHistoryDto> operations) {
        return getOperationsWithCurrency(operations,
                OperationHistoryDto::getDestinationCurrency);
    }

    @MutationMapping
    public VoidResponse createOperation(@Argument String sourceProduct,
                                        @Argument String sourceFund,
                                        @Argument BigDecimal sourceAmount,
                                        @Argument String sourceCurrency,
                                        @Argument String destinationProduct,
                                        @Argument String destinationFund,
                                        @Argument BigDecimal destinationAmount,
                                        @Argument String destinationCurrency,
                                        @Argument String description) {
        OperationHistoryEntity entity = operationHistoryMapper.map(sourceProduct, sourceFund, sourceAmount, sourceCurrency,
                destinationProduct, destinationFund, destinationAmount, destinationCurrency, description);
        operationHistoryService.save(entity);
        return new VoidResponse();
    }

    private Map<OperationHistoryDto, CurrencyModel> getOperationsWithCurrency(
            List<OperationHistoryDto> operations,
            Function<OperationHistoryDto, CurrencyModel> currencyFieldFunction) {

        Map<OperationHistoryDto, CurrencyModel> resultMap = new HashMap<>();
        for (OperationHistoryDto operation : operations) {
            CurrencyModel currency = currencyFieldFunction.apply(operation);
            if (isNull(currency) || isNull(currency.getId())) {
                resultMap.put(operation, null);
                continue;
            }
            CurrencyModel currencyModel = currencyExternalService.get(currency.getId());
            resultMap.put(operation, currencyModel);
        }
        return resultMap;
    }
}
