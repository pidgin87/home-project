package com.smirnoff.home.garden.iot.device.persistance.repository;

import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DeviceEntityRepository extends JpaRepository<DeviceEntity, String> {
    List<DeviceEntity> findByRoomId(String roomId);

    List<DeviceEntity> findByRoomIdInOrderByRoomIdAsc(Collection<String> roomIds, Sort sort);

}
