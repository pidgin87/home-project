package com.smirnoff.home.finance.fund.service.lc;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.platform.session.client.SessionClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FundLifecycleImpl implements FundLifecycle {

    private final SessionClientService sessionClientService;

    @Override
    public FundEntity create() {
        FundEntity entity = new FundEntity();
        entity.setActive(Boolean.TRUE);
        entity.setCompany(sessionClientService.getCompanyId());
        return entity;
    }
}
