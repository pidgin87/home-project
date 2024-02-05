package com.smirnoff.home.garden.iot.device.persistance.repository;

import com.smirnoff.home.garden.iot.device.persistance.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {
}
