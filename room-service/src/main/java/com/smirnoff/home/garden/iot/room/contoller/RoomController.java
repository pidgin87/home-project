package com.smirnoff.home.garden.iot.room.contoller;

import com.smirnoff.home.garden.iot.room.persistance.model.RoomEntity;
import com.smirnoff.home.garden.iot.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @QueryMapping
    public List<RoomEntity> rooms() {
        return roomService.getAll();
    }

    @MutationMapping
    public RoomEntity createRoom(@Argument String name) {
        return roomService.create(name);
    }
}
