package me.wgoddard.PolyannaBackend.services;

import me.wgoddard.PolyannaBackend.entities.Room;
import me.wgoddard.PolyannaBackend.entities.Server;
import me.wgoddard.PolyannaBackend.repos.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomService {

    private final RoomRepository repo;

    private final ServerService serverService;

    @Autowired
    public RoomService(RoomRepository repo, ServerService serverService) {
        this.repo = repo;
        this.serverService = serverService;
    }

    public ResponseEntity<String> save(Room room, Long guildId) {
        room.setServer(serverService.read(guildId));
        repo.saveAndFlush(room);
        return new ResponseEntity<>("The room was saved successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Room>> readAll(Long guildId) {
        return new ResponseEntity<>(repo.findByServerDiscordId(guildId), HttpStatus.OK);
    }

    public ResponseEntity<Room> read(Long guildId, String name, int num) {
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            return new ResponseEntity<>(rooms.get(num-1),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> update(Room room, Long guildId, String name, int num) {
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            room.setId(rooms.get(num-1).getId());
            return save(room, guildId);
        }
        return new ResponseEntity<>("The room could not be found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> delete(Long guildId, String name, int num) {
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            repo.delete(rooms.get(num-1));
            return new ResponseEntity<>("The room was deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("The room could not be found", HttpStatus.BAD_REQUEST);
    }

}
