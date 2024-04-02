package com.smirnoff.home.finance.history.controller;

import com.smirnoff.home.finance.history.mapper.OperationHistoryMapper;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.model.VoidResponse;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.service.operation.OperationHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OperationController {

    private final OperationHistoryService operationHistoryService;
    private final OperationHistoryMapper operationHistoryMapper;

    @MutationMapping
    public VoidResponse createOperation(@Argument OperationHistoryDto operation) {
        OperationHistoryEntity entity = operationHistoryMapper.map(operation);
        operationHistoryService.save(entity);
        return new VoidResponse();
    }
}
