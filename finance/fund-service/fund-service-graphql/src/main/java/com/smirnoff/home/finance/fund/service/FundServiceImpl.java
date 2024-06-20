package com.smirnoff.home.finance.fund.service;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.finance.fund.persistance.repository.FundRepository;
import com.smirnoff.home.finance.fund.service.lc.FundLifecycle;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {

    private final FundLifecycle fundLifecycle;
    private final FundRepository fundRepository;
    private final SessionClientService sessionClientService;

    @Override
    public List<FundEntity> getAll() {
        String companyId = sessionClientService.getCompanyId();
        return fundRepository.findByCompanyIdOrderByCreatedDateAsc(companyId);
    }

    @Override
    public List<FundEntity> getAll(List<String> fundIds) {
        String companyId = sessionClientService.getCompanyId();
        return fundRepository.findByCompanyIdAndIdInOrderByCreatedDateAsc(companyId, fundIds);
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
