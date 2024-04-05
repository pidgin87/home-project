package com.smirnoff.home.finance.history.controller;

import com.smirnoff.home.finance.history.mapper.OperationHistoryMapper;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.model.VoidResponse;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.service.operation.OperationHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
@AllArgsConstructor
public class OperationController {

    private final OperationHistoryService operationHistoryService;
    private final OperationHistoryMapper operationHistoryMapper;

    @QueryMapping
    public List<OperationHistoryDto> getOperationList() {
        List<OperationHistoryEntity> operations = operationHistoryService.getAll();
        return operationHistoryMapper.map(operations);
    }

    @MutationMapping
    public VoidResponse createOperation(@Argument String sourceProduct,
                                        @Argument BigDecimal sourceAmount,
                                        @Argument String sourceCurrency) {
        OperationHistoryEntity entity = operationHistoryMapper.map(sourceProduct, sourceAmount, sourceCurrency);
        operationHistoryService.save(entity);
        return new VoidResponse();
    }
}
