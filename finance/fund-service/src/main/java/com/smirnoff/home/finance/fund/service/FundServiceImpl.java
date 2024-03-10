package com.smirnoff.home.finance.fund.service;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.finance.fund.persistance.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {
    private final FundRepository fundRepository;

    @Override
    public List<FundEntity> getAll() {
        return fundRepository.findAll();
    }
}
