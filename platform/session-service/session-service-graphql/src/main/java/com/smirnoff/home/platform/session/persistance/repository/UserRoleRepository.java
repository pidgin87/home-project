package com.smirnoff.home.platform.session.persistance.repository;

import com.smirnoff.home.platform.session.persistance.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
    List<UserRoleEntity> findBySessionId(String sessionId);
}
