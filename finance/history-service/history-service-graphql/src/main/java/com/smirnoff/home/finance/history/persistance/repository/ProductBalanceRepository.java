package com.smirnoff.home.finance.history.persistance.repository;

import com.smirnoff.home.finance.history.persistance.entity.ProductBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBalanceRepository extends JpaRepository<ProductBalanceEntity, String> {
}
