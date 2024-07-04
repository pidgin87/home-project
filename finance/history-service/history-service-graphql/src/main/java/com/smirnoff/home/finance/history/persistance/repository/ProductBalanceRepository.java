package com.smirnoff.home.finance.history.persistance.repository;

import com.smirnoff.home.finance.history.persistance.entity.ProductBalanceEntity;
import com.smirnoff.home.finance.history.persistance.repository.projections.ProductBalanceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBalanceRepository extends JpaRepository<ProductBalanceEntity, String> {

    @Query(value = """
        select
            new com.smirnoff.home.finance.history.persistance.repository.projections.ProductBalanceProjection(
                balance.product,
                sum(balance.amount)
            )
        from ProductBalanceEntity balance
        where balance.product in (:productIds)
        group by balance.product
    """)
    List<ProductBalanceProjection> getBalanceByProduct(List<String> productIds);
}
