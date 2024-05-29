package com.smirnoff.home.platform.dictionary.persistance.repository;

import com.smirnoff.home.platform.dictionary.persistance.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, String> {
}
