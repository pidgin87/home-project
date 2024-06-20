package com.smirnoff.home.finance.fund.controller;

import com.smirnoff.home.finance.fund.mapper.FundMapper;
import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.fund.model.VoidResponse;
import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.finance.fund.service.FundService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class FundController {

    private final FundService fundService;
    private final FundMapper fundMapper;

    @QueryMapping
    public List<Fund> getFundList() {
        List<FundEntity> funds = fundService.getAll();
        return fundMapper.map(funds);
    }

    @QueryMapping
    public List<Fund> getFundListByIds(@Argument List<String> fundIds) {
        return fundMapper.map(
                fundService.getAll(fundIds)
        );
    }

    @MutationMapping
    public Fund createFund(@Argument String name) {
        FundEntity fund = fundService.create(name);
        return fundMapper.map(fund);
    }

    @MutationMapping
    public Fund updateFund(@Argument String id, @Argument String name) {
        FundEntity fund = fundService.update(id, name);
        return fundMapper.map(fund);
    }

    @MutationMapping
    public VoidResponse deleteFund(@Argument String id) {
        fundService.delete(id);
        return new VoidResponse();
    }
}
