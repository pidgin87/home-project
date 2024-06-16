package com.smirnoff.home.finance.history.controller;

import com.smirnoff.home.finance.history.mapper.OperationHistoryMapper;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.service.currency.CurrencyExternalService;
import com.smirnoff.home.finance.history.service.operation.OperationHistoryService;
import com.smirnoff.home.finance.history.service.service.ProductService;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Controller
@AllArgsConstructor
public class GetOperationListController {

    private final OperationHistoryService operationHistoryService;
    private final OperationHistoryMapper operationHistoryMapper;
    private final CurrencyExternalService currencyExternalService;
    private final ProductService productService;

    @QueryMapping
    public List<OperationHistoryDto> getOperationList() {
        List<OperationHistoryEntity> operations = operationHistoryService.getAll();
        return operationHistoryMapper.map(operations);
    }

    @BatchMapping(typeName = "Operation", field = "sourceProduct")
    public Map<OperationHistoryDto, ProductModel> sourceProduct(List<OperationHistoryDto> operations) {
        List<String> productList = operations.stream()
                .filter(operation -> Objects.nonNull(operation.getSourceProduct()))
                .map(operation -> operation.getSourceProduct().id())
                .toList();

        Map<String, ProductModel> collect = productService
                .getByIds(productList).stream()
                .collect(Collectors.toMap(ProductModel::id, Function.identity()));

        Map<OperationHistoryDto, ProductModel> result = new HashMap<>();
        for (OperationHistoryDto operation : operations) {
            String sourceProductId = operation.getSourceProduct().id();
            result.put(operation, collect.get(sourceProductId));
        }

        return result;
    }

    @BatchMapping(typeName = "Operation", field = "destinationProduct")
    public Map<OperationHistoryDto, ProductModel> destinationProduct(List<OperationHistoryDto> operations) {
        List<String> productList = operations.stream()
                .filter(operation -> Objects.nonNull(operation.getDestinationProduct()))
                .map(operation -> operation.getDestinationProduct().id())
                .toList();

        Map<String, ProductModel> collect = productService
                .getByIds(productList).stream()
                .collect(Collectors.toMap(ProductModel::id, Function.identity()));

        Map<OperationHistoryDto, ProductModel> result = new HashMap<>();
        for (OperationHistoryDto operation : operations) {
            String destinationProductId = operation.getDestinationProduct().id();
            result.put(operation, collect.get(destinationProductId));
        }

        return result;
    }

    @BatchMapping(typeName = "Operation", field = "sourceCurrency")
    public Map<OperationHistoryDto, CurrencyModel> sourceCurrency(List<OperationHistoryDto> operations) {
        return getOperationsWithCurrency(operations, OperationHistoryDto::getSourceCurrency);
    }

    @BatchMapping(typeName = "Operation", field = "destinationCurrency")
    public Map<OperationHistoryDto, CurrencyModel> destinationCurrency(List<OperationHistoryDto> operations) {
        return getOperationsWithCurrency(operations, OperationHistoryDto::getDestinationCurrency);
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
