package com.smirnoff.home.finance.fund.mapper;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FundMapper {
    List<Fund> map(List<FundEntity> funds);

    Fund map(FundEntity fund);
}
