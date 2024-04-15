package com.smirnoff.home.ui.service.finance.history;

import com.smirnoff.home.finance.history.client.OperationHistoryClient;
import com.smirnoff.home.finance.history.client.model.GetOperationHistoryDtoList;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.model.finance.fund.FundModel;
import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class OperationHistoryServiceImpl implements OperationHistoryService {

    private final OperationHistoryClient operationHistoryClient;

    //language=graphql
    private static final String CREATE_OPERATION = """
            mutation CreateOperation($sourceProduct: String,
                                     $sourceAmount: Float,
                                     $sourceCurrency: String) {
                createOperation(sourceProduct: $sourceProduct,
                                sourceAmount: $sourceAmount,
                                sourceCurrency: $sourceCurrency) {
                    result
                }
            }
            """;

    //language=graphql
    private static final String GET_OPERATION_LIST = """
            query GetOperationList {
                getOperationList {
                    id
                    sourceAmount
                    sourceCurrency {
                        symbol
                    }
                    destinationAmount
                    destinationCurrency {
                        symbol          
                    }
                    createdDate
                }
            }
            """;

    @Override
    public void delete(OperationHistoryDto operation) {

    }

    @Override
    public void update(OperationHistoryModel operation) {

    }

    @Override
    public Collection<OperationHistoryDto> getList() {
        GraphQlResponse<GetOperationHistoryDtoList> response = operationHistoryClient.getOperationList(GraphQlRequest.builder()
                .query(GET_OPERATION_LIST)
                .operationName("GetOperationList")
                .build()
        );

        return response.getData().getOperationList();
    }

    @Override
    public void create(OperationHistoryModel operation) {
        operationHistoryClient.createOperation(GraphQlRequest.builder()
                .query(CREATE_OPERATION)
                .operationName("CreateOperation")
                .variables(new GraphQlVariables()
                        .addObject("sourceProduct", operation.getSourceProduct(), ProductModel::id)
                        .addObject("sourceFund", operation.getSourceFund(), FundModel::id)
                        .addObject("sourceAmount", operation.getSourceAmount())
                        .addObject("sourceCurrency", operation.getSourceCurrency(), CurrencyModel::getId)

                        .addObject("destinationProduct", operation.getDestinationProduct(), ProductModel::id)
                        .addObject("destinationFund", operation.getDestinationFund(), FundModel::id)
                        .addObject("destinationAmount", operation.getDestinationAmount())
                        .addObject("destinationCurrency", operation.getDestinationCurrency(), CurrencyModel::getId)

                        .addObject("description", operation.getDescription(), value -> isEmpty(value) ? null : value)
                )
                .build());
    }
}
