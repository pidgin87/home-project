package com.smirnoff.home.garden.iot.device.service;

import com.smirnoff.home.garden.iot.device.model.Device;
import com.smirnoff.home.garden.iot.device.model.DeviceStatus;
import com.smirnoff.home.garden.iot.device.persistance.model.DeviceEntity;
import com.smirnoff.home.garden.iot.device.persistance.repository.DeviceEntityRepository;
import lombok.AllArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceEntityRepository deviceEntityRepository;
    private final ProducerTemplate producerTemplate;

    @Override
    public List<DeviceEntity> getAll() {
        return deviceEntityRepository.findAll();
    }

    @Override
    public Optional<DeviceEntity> findById(String id) {
        return deviceEntityRepository.findById(id);
    }

    @Override
    public List<DeviceEntity> getAll(String roomId) {
        return deviceEntityRepository.findByRoomId(roomId);
    }

    @Override
    public List<DeviceEntity> getAll(List<String> roomIds) {
        return deviceEntityRepository.findByRoomIdInOrderByRoomIdAsc(
                roomIds, Sort.by(Sort.Direction.ASC, "roomId")
        );
    }

    @Override
    public List<DeviceStatus> getStatuses(Device device) {
        Object response = producerTemplate
                .requestBody("direct:get-device-statuses", device.getId());
        return null;
    }
}
