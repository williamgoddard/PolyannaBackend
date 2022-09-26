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
    public Room create(@RequestBody Room room) { return service.save(room); }

    @GetMapping(path = "readAll")
    public List<Room> readAll() { return service.getAll(); }

    @GetMapping(path = "read/{name}")
    public Room readByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PutMapping(path = "update")
    public Room update(@RequestBody Room room) { return service.save(room); }

    @DeleteMapping(path="delete/{name}")
    public String delete(@PathVariable String name) { return service.deleteByName(name); }

}
