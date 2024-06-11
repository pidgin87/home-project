package com.smirnoff.home.platform.dictionary.persistance.repository;

import com.smirnoff.home.platform.dictionary.persistance.entity.currencypair.CurrencyPairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyPairRepository extends JpaRepository<CurrencyPairEntity, String> {
}
