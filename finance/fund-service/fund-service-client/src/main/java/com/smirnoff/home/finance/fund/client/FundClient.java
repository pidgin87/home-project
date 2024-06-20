package com.smirnoff.home.finance.fund.client;

import com.smirnoff.home.finance.fund.model.GetFundByIdsModelList;
import com.smirnoff.home.finance.fund.model.GetFundModelList;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "finance-fund-graphql", path = "/api/finance/fund")
public interface FundClient {

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetFundModelList> getFunds(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse<GetFundByIdsModelList> getFundsByIds(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse.VoidGraphQlResponse createFund(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse.VoidGraphQlResponse updateFund(@RequestBody GraphQlRequest request);

    @RequestMapping(method = RequestMethod.POST)
    GraphQlResponse.VoidGraphQlResponse deleteFund(@RequestBody GraphQlRequest request);

}
