package com.smirnoff.home.platform.dictionary.persistance.repository;

import com.smirnoff.home.platform.dictionary.persistance.entity.currency.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {
}
