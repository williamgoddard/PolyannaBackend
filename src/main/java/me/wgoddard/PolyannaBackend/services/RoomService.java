package me.wgoddard.PolyannaBackend.services;

import me.wgoddard.PolyannaBackend.entities.Room;
import me.wgoddard.PolyannaBackend.entities.Server;
import me.wgoddard.PolyannaBackend.repos.RoomRepository;
import me.wgoddard.PolyannaBackend.repos.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoomService {

    private final RoomRepository repo;

    @Autowired
    public RoomService(RoomRepository repo) {
        this.repo = repo;
    }

    public void save(Room room) {
        repo.saveAndFlush(room);
    }

    public void deleteByName(String name) {
        repo.deleteByName(name);
    }

    public Room getByName(String name) {
        Optional<Room> room = repo.findByName(name);
        if (room.isPresent()) {
            return room.get();
        } else {
            return null;
        }
    }

    public List<Room> getAll() {
        return repo.findAll();
    }

}
