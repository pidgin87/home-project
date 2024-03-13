package com.smirnoff.home.finance.fund.service;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.finance.fund.persistance.repository.FundRepository;
import com.smirnoff.home.finance.fund.service.lc.FundLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {
    private final FundLifecycle fundLifecycle;
    private final FundRepository fundRepository;

    @Override
    public List<FundEntity> getAll() {
        return fundRepository.findAll();
    }

    @Override
    public FundEntity create(String name) {
        FundEntity fund = fundLifecycle.create();
        fund.setName(name);

        return fundRepository.save(fund);
    }

    @Override
    public void delete(String id) {
        fundRepository.deleteById(id);
    }
}
