package me.wgoddard.PolyannaBackend.controllers;

import me.wgoddard.PolyannaBackend.entities.Room;
import me.wgoddard.PolyannaBackend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/room")
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping(path = "create")
    public void create(@RequestBody Room room) {
        service.save(room);
    }

    @GetMapping(path = "readAll")
    public List<Room> readAll() {
        return service.getAll();
    }

    @GetMapping(path = "readByName")
    public Room readByName(@RequestBody String name) {
        return service.getByName(name);
    }

    @PutMapping(path = "update")
    public void update(@RequestBody Room room) { service.save(room); }

    @DeleteMapping(path="delete")
    public void delete(@RequestBody String name) { service.deleteByName(name); }

}
