package me.wgoddard.PolyannaBackend.room;

import me.wgoddard.PolyannaBackend.response.Response;
import me.wgoddard.PolyannaBackend.server.Server;
import me.wgoddard.PolyannaBackend.server.ServerService;
import me.wgoddard.PolyannaBackend.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomService {

    private final RoomRepository repo;
    private final ServerService serverService;
    private final InventoryService inventoryService;

    @Autowired
    public RoomService(RoomRepository repo, ServerService serverService, InventoryService inventoryService) {
        this.repo = repo;
        this.serverService = serverService;
        this.inventoryService = inventoryService;
    }

    public Response<Room> save(Room room, Long guildId) {
        Response<Server> server = serverService.read(guildId);
        if (!server.success) {
            return new Response<>(null, server.response, false);
        }
        room.setServer(server.data);
        room.setInventory(inventoryService.create(guildId).getBody());
        repo.saveAndFlush(room);
        return new Response(room, "Room saved successfully.", true);
    }

    public Response<Room> read(Long guildId, String name, int num) {
        Response<Server> server = serverService.read(guildId);
        if (!server.success) {
            return new Response<>(null, server.response, false);
        }
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (num >= 1 && rooms.size() >= num) {
            return new Response<>(rooms.get(num-1), "Room returned successfully.", true);
        } else if (rooms.size() == 0) {
            return new Response<>(null, "Room `" + name + "` could not be found.", false);
        }
        String response = "Room number for `" + name + "` must be between `1` and `" +  rooms.size() + "`.";
        return new Response<>(null, response, false);
    }

    public Response<List<Room>> readAll(Long guildId) {
        Response<Server> server = serverService.read(guildId);
        if (!server.success) {
            return new Response<>(null, server.response, false);
        }
        return new Response<>(repo.findByServerDiscordId(guildId), "List of rooms returned successfully.", false);
    }

    public Response<Room> update(Room room, Long guildId, String name, int num) {
        Response<Server> server = serverService.read(guildId);
        if (!server.success) {
            return new Response<>(null, server.response, false);
        }
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            room.setId(rooms.get(num-1).getId());
            return save(room, guildId);
        }
        return new Response<>(null,"Room `\" + name + \"` could not be found.", false);
    }

    public Response<Room> delete(Long guildId, String name, int num) {
        Response<Server> server = serverService.read(guildId);
        if (!server.success) {
            return new Response<>(null, server.response, false);
        }
        List<Room> rooms = repo.findByServerDiscordIdAndName(guildId, name);
        if (rooms.size() >= num) {
            repo.delete(rooms.get(num-1));
            return new Response<>(null, "Room deleted successfully.", true);
        }
        return new Response<>(null, "Room `\" + name + \"` could not be found", false);
    }

}
