package me.wgoddard.PolyannaBackend.services;

import me.wgoddard.PolyannaBackend.entities.Server;
import me.wgoddard.PolyannaBackend.repos.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void addNewServer(Server server) {
//        if (repo.findStudentByEmail(server.getEmail()).isPresent()) {
//            throw new IllegalStateException("Email taken");
//        }
        repo.save(server);
    }
}
