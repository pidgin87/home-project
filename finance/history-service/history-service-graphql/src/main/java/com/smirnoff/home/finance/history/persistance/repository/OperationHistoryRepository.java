package com.smirnoff.home.finance.history.persistance.repository;

import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, String> {

    List<OperationHistoryEntity> findByCompanyIdOrderByCreatedDateDesc(String companyId);

}
