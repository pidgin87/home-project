package com.smirnoff.home.finance.history.controller;

import com.smirnoff.home.finance.history.mapper.BalanceProductMapper;
import com.smirnoff.home.finance.history.model.ProductBalanceDto;
import com.smirnoff.home.finance.history.persistance.repository.projections.ProductBalanceProjection;
import com.smirnoff.home.finance.history.service.balance.BalanceProductService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GetProductBalanceController {

    private final BalanceProductService balanceProductService;
    private final BalanceProductMapper balanceProductMapper;

    @QueryMapping
    public List<ProductBalanceDto> getBalanceByProductList(@Argument List<String> productIds) {
        List<ProductBalanceProjection> operations = balanceProductService.getByIds(productIds);
        return balanceProductMapper.map(operations);
    }

}
