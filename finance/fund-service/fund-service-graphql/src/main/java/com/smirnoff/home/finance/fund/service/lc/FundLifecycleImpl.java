package com.smirnoff.home.finance.fund.service.lc;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
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
        entity.setCompanyId(sessionClientService.getCompanyId());
        return entity;
    }
}
