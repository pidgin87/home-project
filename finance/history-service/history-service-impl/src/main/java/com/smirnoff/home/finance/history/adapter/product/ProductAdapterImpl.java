package com.smirnoff.home.finance.history.adapter.product;

import com.smirnoff.home.finance.history.adapter.product.client.FinanceProductServiceClient;
import com.smirnoff.home.finance.history.model.ProductDto;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAdapterImpl implements ProductAdapter {

    private final FinanceProductServiceClient financeProductServiceClient;

    //language=graphql
    private static final String GET_PRODUCT_REQUEST = """
            mutation GetProduct($name: String, $type: String) {
                createProduct(name: $name, type: $type) {
                    id
                }
            }
            """;

    @Override
    public ProductDto getById(String productId) {
        financeProductServiceClient.getProduct(GraphQlRequest.builder()
                .operationName("GetProduct")
                .build());
        return null;
    }
}
