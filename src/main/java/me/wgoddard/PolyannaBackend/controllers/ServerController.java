package me.wgoddard.PolyannaBackend.controllers;

import me.wgoddard.PolyannaBackend.entities.Server;
import me.wgoddard.PolyannaBackend.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/server")
public class ServerController {

    private final ServerService service;

    @Autowired
    public ServerController(ServerService service) {
        this.service = service;
    }

    @PostMapping
    public void registerNewServer(@RequestBody Server server) {
        service.addNewServer(server);
    }

}
