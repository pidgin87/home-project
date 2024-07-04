package com.smirnoff.home.finance.product.adapter.history;

import com.smirnoff.home.finance.history.client.OperationHistoryClient;
import com.smirnoff.home.finance.history.client.model.GetProductBalanceDtoList;
import com.smirnoff.home.finance.history.model.ProductBalanceDto;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OperationHistoryAdapterImpl implements OperationHistoryAdapter {
    private final OperationHistoryClient operationHistoryClient;

    //language=graphql
    private static final String REQUESTS = """
            query GetProductListByIds($productIds: [String]) {
                getBalanceByProductList(productIds: $productIds) {
                    productId
                    amount
                }
            }
            """;

    @Override
    public List<ProductBalanceDto> getList(List<String> productIds) {
        GraphQlResponse<GetProductBalanceDtoList> response = operationHistoryClient.getBalanceByProductList(GraphQlRequest.builder()
                .query(REQUESTS)
                .operationName("GetProductListByIds")
                .variables(new GraphQlVariables()
                        .addObject("productIds", productIds)
                ).build()
        );

        GetProductBalanceDtoList dtoList = response.getData();
        if (Objects.isNull(dtoList)) {
            return Collections.emptyList();
        }

        List<ProductBalanceDto> balances = dtoList.getBalanceByProductList();
        if (CollectionUtils.isEmpty(balances)) {
            return Collections.emptyList();
        }
        return balances;
    }
}
