package com.smirnoff.home.finance.history.client.model;

import com.smirnoff.home.finance.history.model.ProductBalanceDto;

import java.util.List;

public record GetProductBalanceDtoList(List<ProductBalanceDto> getBalanceByProductList) {
}
