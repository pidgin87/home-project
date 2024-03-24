package com.smirnoff.home.ui.adapter.finance.product.client;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "finance-product-service", path = "/api/finance/product")
public interface FinanceProductServiceClient {
    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetProductModelList> getProducts(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    String getProductsAsString(@RequestBody GraphQlRequest request);
}
