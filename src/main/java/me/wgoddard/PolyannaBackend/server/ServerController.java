package me.wgoddard.PolyannaBackend.server;

import me.wgoddard.PolyannaBackend.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response<Server> create(@RequestBody Server server) {
        return service.create(server);
    }

}
