package com.smirnoff.home.platform.user.profile.persistance.repository;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
    List<UserRoleEntity> findByUserProfile_IdIn(List<String> ids);
}
