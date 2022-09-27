package me.wgoddard.PolyannaBackend.services;

import me.wgoddard.PolyannaBackend.entities.Room;
import me.wgoddard.PolyannaBackend.entities.Server;
import me.wgoddard.PolyannaBackend.repos.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Room save(Room room, Long guildId) {
        room.setServer(serverService.read(guildId));
        return repo.saveAndFlush(room);
    }

    public List<Room> readAll(Long guildId) {
        return repo.findByServerDiscordId(guildId);
    }

    public Room read(Long guildId, String name, int num) {
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            return rooms.get(num-1);
        }
        return null;
    }

    public Room update(Room room, Long guildId, String name, int num) {
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            room.setId(rooms.get(num-1).getId());
            return save(room, guildId);
        }
        return null;
    }

    public String delete(Long guildId, String name, int num) {
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            repo.delete(rooms.get(num-1));
            return "Room deleted successfully";
        }
        return null;
    }

}
