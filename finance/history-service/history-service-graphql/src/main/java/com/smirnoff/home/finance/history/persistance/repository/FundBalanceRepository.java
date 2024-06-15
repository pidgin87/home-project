package com.smirnoff.home.finance.history.persistance.repository;

import com.smirnoff.home.finance.history.persistance.entity.FundBalanceEntity;
import com.smirnoff.home.finance.history.persistance.entity.ProductBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundBalanceRepository extends JpaRepository<FundBalanceEntity, String> {
}
