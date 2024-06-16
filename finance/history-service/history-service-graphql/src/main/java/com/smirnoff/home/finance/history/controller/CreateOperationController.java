package com.smirnoff.home.finance.history.controller;

import com.smirnoff.home.finance.history.mapper.OperationHistoryMapper;
import com.smirnoff.home.finance.history.model.VoidResponse;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.service.operation.OperationHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class CreateOperationController {
    private final OperationHistoryService operationHistoryService;
    private final OperationHistoryMapper operationHistoryMapper;

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
}
