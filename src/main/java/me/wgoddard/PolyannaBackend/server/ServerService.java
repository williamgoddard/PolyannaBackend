package me.wgoddard.PolyannaBackend.server;

import me.wgoddard.PolyannaBackend.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServerService {

    private final ServerRepository repo;

    @Autowired
    public ServerService(ServerRepository repo) {
        this.repo = repo;
    }

    public Response<Server> create(Server server) {
        if (repo.findByDiscordId(server.getDiscordId()).isPresent()) {
            return new Response<>(null, "That server is already registered.", false);
        }
        server = repo.saveAndFlush(server);
        return new Response<>(server, "New server registered: " + server.getDiscordId(), true);
    }

    public Response<Server> read(Long discordId) {
        Optional<Server> server = repo.findByDiscordId(discordId);
        if (server.isPresent()) {
            return new Response<>(server.get(), "Server returned successfully.", true);
        }
        return new Response<>(null, "Server could not be found: " + discordId, false);
    }

    public Response<List<Server>> readAll() {
        List<Server> servers = repo.findAll();
        return new Response<>(servers, "List of servers returned successfully.", true);
    }

}
