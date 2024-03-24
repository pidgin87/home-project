package com.smirnoff.home.ui.adapter.finance.product;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.ui.adapter.finance.product.client.FinanceProductServiceClient;
import com.smirnoff.home.ui.adapter.finance.product.client.GetProductModelList;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductAdapterImpl implements ProductAdapter {
    private final FinanceProductServiceClient financeProductServiceClient;

    private static final String GET_PRODUCT_REQUEST = """
            query GetProductList {
                getProductList {
                    id
                    name
                    type
                }
            }
            """;

    @Override
    public List<ProductModel> getList() {
        GraphQlResponse<GetProductModelList> products = financeProductServiceClient.getProducts(GraphQlRequest.builder()
                .query(GET_PRODUCT_REQUEST)
                .operationName("GetProductList")
                .build()
        );
        return products.getData().getProductList();
    }

    @Override
    public void create(String productName, ProductTypeModel productType) {

    }
}
