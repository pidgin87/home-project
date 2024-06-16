package com.smirnoff.home.finance.history.adapter.product;

import com.smirnoff.home.finance.product.client.FinanceProductServiceClient;
import com.smirnoff.home.finance.product.client.GetProductModelList;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ProductAdapterImpl implements ProductAdapter {

    private final FinanceProductServiceClient financeProductServiceClient;

    //language=graphql
    private static final String GRAPHQL_REQUESTS = """
            query GetProductList {
                getProductListByIds(productIds: [String]) {
                    id
                    name
                    type
                }
            }
            """;

    @Override
    public List<ProductModel> getByIds(List<String> productList) {
        GraphQlResponse<GetProductModelList> products = financeProductServiceClient.getProducts(GraphQlRequest.builder()
                .query(GRAPHQL_REQUESTS)
                .operationName("GetProduct")
                .build());

        if (isNull(products.getData())) {
            return Collections.emptyList();
        }

        return products.getData().getProductList();
    }
}
