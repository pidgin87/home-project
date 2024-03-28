package com.smirnoff.home.finance.history.persistance.repository;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, String> {

}
