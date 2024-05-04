package com.smirnoff.home.platform.session.persistance.repository;

import com.smirnoff.home.platform.session.persistance.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, String> {
}
