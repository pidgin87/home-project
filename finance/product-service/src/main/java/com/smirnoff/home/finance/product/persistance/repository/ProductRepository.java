package com.smirnoff.home.finance.product.persistance.repository;

import com.smirnoff.home.finance.product.persistance.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
