package me.wgoddard.PolyannaBackend.services;

import me.wgoddard.PolyannaBackend.entities.Server;
import me.wgoddard.PolyannaBackend.repos.ServerRepository;
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

    public List<Server> getServers() {
        return repo.findAll();
    }

    public ResponseEntity<String> create(Server server) {
        if (repo.findByDiscordId(server.getDiscordId()).isPresent()) {
            return new ResponseEntity<>("That server is already registered.", HttpStatus.BAD_REQUEST);
        }
        repo.saveAndFlush(server);
        return new ResponseEntity<>("New server registered: " + server.getDiscordId(), HttpStatus.OK);
    }

    public Server read(Long discordId) {
        Optional<Server> server = repo.findByDiscordId(discordId);
        if (server.isPresent()) {
            return server.get();
        }
        return null;
    }

}
