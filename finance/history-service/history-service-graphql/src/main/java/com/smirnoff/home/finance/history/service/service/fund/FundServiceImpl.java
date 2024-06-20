package com.smirnoff.home.finance.history.service.service.fund;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.history.adapter.fund.FundAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {

    private final FundAdapter fundAdapter;

    @Override
    public List<Fund> getByIds(List<String> fundIds) {
        return fundAdapter.getByIds(fundIds);
    }
}
