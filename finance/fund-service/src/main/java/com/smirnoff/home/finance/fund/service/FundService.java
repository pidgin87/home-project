package com.smirnoff.home.finance.fund.service;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;

import java.util.List;

public interface FundService {
    List<FundEntity> getAll();

    FundEntity create(String name);

    FundEntity update(String id, String name);

    void delete(String id);
}
