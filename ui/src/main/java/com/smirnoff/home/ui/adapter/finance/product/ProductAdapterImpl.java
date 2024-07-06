package com.smirnoff.home.ui.adapter.finance.product;

import com.smirnoff.home.finance.product.client.FinanceProductServiceClient;
import com.smirnoff.home.finance.product.client.GetProductModelList;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.finance.product.model.ProductTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductAdapterImpl implements ProductAdapter {
    private final FinanceProductServiceClient financeProductServiceClient;

    //language=graphql
    private static final String REQUESTS = """
            query GetProductList {
                getProductList {
                    id
                    name
                    type
                    amount {
                        amount
                        currency {
                            name
                        }
                    }
                }
            }
            mutation CreateProduct($name: String, $type: String) {
                createProduct(name: $name, type: $type) {
                    id
                }
            }
            mutation DeleteFund($productId: String) {
                deleteProduct(productId: $productId) {
                    result
                }
            }
            """;

    @Override
    public List<ProductModel> getList() {
        GraphQlResponse<GetProductModelList> products = financeProductServiceClient.getProducts(GraphQlRequest.builder()
                .query(REQUESTS)
                .operationName("GetProductList")
                .build()
        );
        return products.getData().getProductList();
    }

    @Override
    public void create(String productName, ProductTypeModel productType) {
        financeProductServiceClient.createProduct(GraphQlRequest.builder()
                .query(REQUESTS)
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
                .query(REQUESTS)
                .operationName("DeleteFund")
                .variables(new GraphQlVariables()
                        .addObject("productId", product.id()))
                .build()
        );
    }
}
