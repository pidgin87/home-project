package com.smirnoff.home.finance.fund.controller;

import com.smirnoff.home.finance.fund.mapper.FundMapper;
import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.finance.fund.service.FundService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class FundController {

    private final FundService fundService;
    private final FundMapper fundMapper;

    @QueryMapping
    public List<Fund> getDevices() {
        List<FundEntity> funds = fundService.getAll();
        return fundMapper.map(funds);
    }

}
