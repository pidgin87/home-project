package com.smirnoff.home.finance.history.adapter.product;

import com.smirnoff.home.finance.product.client.FinanceProductServiceClient;
import com.smirnoff.home.finance.product.client.GetProductByIdsModelList;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
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
            query GetProductListByIds($productIds: [String]) {
                getProductListByIds(productIds: $productIds) {
                    id
                    name
                    type
                }
            }
            """;

    @Override
    public List<ProductModel> getByIds(List<String> productList) {
        GraphQlResponse<GetProductByIdsModelList> products = financeProductServiceClient.getProductsByIds(GraphQlRequest.builder()
                .query(GRAPHQL_REQUESTS)
                .operationName("GetProductListByIds")
                .variables(new GraphQlVariables()
                        .addObject("productIds", productList))
                .build());

        GetProductByIdsModelList productsData = products.getData();
        if (isNull(productsData)) {
            return Collections.emptyList();
        }

        return productsData.getProductListByIds();
    }
}
