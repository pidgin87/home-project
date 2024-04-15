package com.smirnoff.home.ui.adapter.finance.product;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.ui.adapter.finance.product.client.FinanceProductServiceClient;
import com.smirnoff.home.ui.adapter.finance.product.client.GetProductModelList;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductAdapterImpl implements ProductAdapter {
    private final FinanceProductServiceClient financeProductServiceClient;

    //language=graphql
    private static final String GET_PRODUCT_REQUEST = """
            query GetProductList {
                getProductList {
                    id
                    name
                    type
                }
            }
            """;

    //language=graphql
    private static final String CREATE_PRODUCT_REQUEST = """
            mutation CreateProduct($name: String, $type: String) {
                createProduct(name: $name, type: $type) {
                    id
                }
            }
            """;

    //language=graphql
    private static final String DELETE_PRODUCT_REQUEST = """
            mutation DeleteFund($productId: String) {
                deleteProduct(productId: $productId) {
                    result
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
        financeProductServiceClient.createProduct(GraphQlRequest.builder()
                .query(CREATE_PRODUCT_REQUEST)
                .operationName("CreateProduct")
                .variables(new GraphQlVariables()
                        .addObject("name", productName)
                        .addObject("type", productType)
                )
                .build()
        );
    }

    @Override
    public void delete(ProductModel product) {
        financeProductServiceClient.deleteProduct(GraphQlRequest.builder()
                .query(DELETE_PRODUCT_REQUEST)
                .operationName("DeleteFund")
                .variables(new GraphQlVariables()
                        .addObject("productId", product.id()))
                .build()
        );
    }
}
