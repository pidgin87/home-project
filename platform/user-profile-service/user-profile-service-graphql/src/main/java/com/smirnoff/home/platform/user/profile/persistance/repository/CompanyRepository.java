package com.smirnoff.home.platform.user.profile.persistance.repository;

import com.smirnoff.home.platform.user.profile.persistance.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {
}
