package com.smirnoff.home.garden.iot.room.persistance.repository;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomEntityRepository extends JpaRepository<RoomEntity, Long> {
}
