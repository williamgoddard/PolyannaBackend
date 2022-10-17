package me.wgoddard.PolyannaBackend.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/server")
public class ServerController {

    private final ServerService service;

    @Autowired
    public ServerController(ServerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Server server) {
        return service.create(server);
    }

}
