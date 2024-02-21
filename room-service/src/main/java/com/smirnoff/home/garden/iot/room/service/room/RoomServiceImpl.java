package com.smirnoff.home.garden.iot.room.service.room;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;
import com.smirnoff.home.garden.iot.room.persistance.repository.RoomEntityRepository;
import com.smirnoff.home.garden.iot.room.service.room.lc.RoomLifecycle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomEntityRepository roomEntityRepository;
    private final RoomLifecycle roomLifecycle;

    @Override
    public List<RoomEntity> getAll() {
        return roomEntityRepository.findAll();
    }

    @Override
    public RoomEntity create(String name) {
        RoomEntity entity = roomLifecycle.createNew(name);
        return roomEntityRepository.save(entity);
    }

    @Override
    public Optional<RoomEntity> getRoom(String roomId) {
        return roomEntityRepository.findById(roomId);
    }
}
