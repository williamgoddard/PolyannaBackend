package me.wgoddard.PolyannaBackend.room;

import me.wgoddard.PolyannaBackend.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/room")
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping(path = "{guildId}")
    public Response<Room> create(@RequestBody Room room, @PathVariable Long guildId) {
        return service.save(room, guildId);
    }

    @GetMapping(path = "{guildId}")
    public Response<List<Room>> readAll(@PathVariable Long guildId) {
        return service.readAll(guildId);
    }

    @GetMapping(path = "{guildId}/{name}")
    public Response<Room> read(@PathVariable Long guildId, @PathVariable String name) {
        return service.read(guildId, name, 1);
    }

    @GetMapping(path = "{guildId}/{name}/{num}")
    public Response<Room> read(@PathVariable Long guildId, @PathVariable String name, @PathVariable int num) {
        return service.read(guildId, name, num);
    }

    @PutMapping(path = "{guildId}/{name}")
    public Response<Room> update(@RequestBody Room room, @PathVariable Long guildId, @PathVariable String name) {
        return service.update(room, guildId, name, 1);
    }

    @PutMapping(path = "{guildId}/{name}/{num}")
    public Response<Room> update(@RequestBody Room room, @PathVariable Long guildId, @PathVariable String name, @PathVariable int num) {
        return service.update(room, guildId, name, num);
    }

    @DeleteMapping(path="{guildId}/{name}")
    public Response<Room> delete(@PathVariable Long guildId, @PathVariable String name) {
        return service.delete(guildId, name, 1);
    }

    @DeleteMapping(path="{guildId}/{name}/{num}")
    public Response<Room> delete(@PathVariable Long guildId, @PathVariable String name, @PathVariable int num) {
        return service.delete(guildId, name, num);
    }

}
