package com.smirnoff.home.finance.fund.persistance.repository;

import com.smirnoff.home.finance.fund.persistance.entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<FundEntity, String> {
}
