package com.smirnoff.home.platform.user.profile.persistance.repository;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfileEntity, String> {
    Optional<UserProfileEntity> findByEmail(String email);
}
