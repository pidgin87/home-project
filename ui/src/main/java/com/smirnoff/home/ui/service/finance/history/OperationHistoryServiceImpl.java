package com.smirnoff.home.ui.service.finance.history;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.history.client.OperationHistoryClient;
import com.smirnoff.home.finance.history.client.model.GetOperationHistoryDtoList;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;
import com.smirnoff.home.finance.product.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class OperationHistoryServiceImpl implements OperationHistoryService {

    private final OperationHistoryClient operationHistoryClient;

    //language=graphql
    private static final String CREATE_OPERATION = """
            mutation CreateOperation(
                                     $sourceProduct: String,
                                     $sourceFund: String,
                                     $sourceAmount: Float,
                                     $sourceCurrency: String,
                                     $destinationProduct: String,
                                     $destinationFund: String,
                                     $destinationAmount: Float,
                                     $destinationCurrency: String,
                                     $description: String,
                                     $createdDate: DateTime) {
                createOperation(sourceProduct: $sourceProduct,
                                sourceFund: $sourceFund,
                                sourceAmount: $sourceAmount,
                                sourceCurrency: $sourceCurrency,
                                destinationProduct: $destinationProduct,
                                destinationFund: $destinationFund,
                                destinationAmount: $destinationAmount,
                                destinationCurrency: $destinationCurrency,
                                description: $description,
                                createdDate: $createdDate) {
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
                    sourceProduct {
                        id
                        name
                    }
                    sourceFund {
                        id
                        name
                    }
                    destinationAmount
                    destinationProduct {
                        id
                        name
                    }
                    destinationFund {
                        id
                        name
                    }
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

        GetOperationHistoryDtoList data = response.getData();
        return data == null ? List.of() : data.getOperationList();
    }

    @Override
    public void create(OperationHistoryModel operation) {
        operationHistoryClient.createOperation(GraphQlRequest.builder()
                .query(CREATE_OPERATION)
                .operationName("CreateOperation")
                .variables(new GraphQlVariables()
                        .addObject("sourceProduct", operation.getSourceProduct(), ProductModel::id)
                        .addObject("sourceFund", operation.getSourceFund(), Fund::id)
                        .addObject("sourceAmount", operation.getSourceAmount())
                        .addObject("sourceCurrency", operation.getSourceCurrency(), CurrencyModel::getId)

                        .addObject("destinationProduct", operation.getDestinationProduct(), ProductModel::id)
                        .addObject("destinationFund", operation.getDestinationFund(), Fund::id)
                        .addObject("destinationAmount", operation.getDestinationAmount())
                        .addObject("destinationCurrency", operation.getDestinationCurrency(), CurrencyModel::getId)

                        .addObject("description", operation.getDescription(), value -> isEmpty(value) ? null : value)
                        .addObject("createdDate", operation.getCreatedDate())
                )
                .build());
    }
}
