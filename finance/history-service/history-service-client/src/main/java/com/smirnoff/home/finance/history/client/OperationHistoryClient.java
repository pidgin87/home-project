package com.smirnoff.home.finance.history.client;

import com.smirnoff.home.finance.history.client.model.GetOperationHistoryDtoList;
import com.smirnoff.home.finance.history.model.VoidResponse;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "finance-operation-history-graphql", path = "/api/finance/operation-history")
public interface OperationHistoryClient {
    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<VoidResponse> createOperation(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetOperationHistoryDtoList> getOperationList(@RequestBody GraphQlRequest request);
}
