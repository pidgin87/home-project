package com.smirnoff.home.finance.fund.service;

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
        return fundRepository.findAllByOrderByCreatedDateAsc();
    }

    @Override
    public FundEntity create(String name) {
        FundEntity fund = fundLifecycle.create();
        fund.setName(name);

        return fundRepository.save(fund);
    }

    @Override
    public FundEntity update(String id, String name) {
        FundEntity fund = fundRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        fund.setName(name);
        return fundRepository.save(fund);
    }

    @Override
    public void delete(String id) {
        fundRepository.deleteById(id);
    }
}
