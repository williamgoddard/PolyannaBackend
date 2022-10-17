package me.wgoddard.PolyannaBackend.controllers;

import me.wgoddard.PolyannaBackend.entities.Character;
import me.wgoddard.PolyannaBackend.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/character")
public class CharacterController {

    private final CharacterService service;

    @Autowired
    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @PostMapping(path = "{guildId}")
    public ResponseEntity<String> create(@RequestBody Character character, @PathVariable Long guildId) {
        return service.save(character, guildId);
    }

    @GetMapping(path = "{guildId}")
    public ResponseEntity<List<Character>> readAll(@PathVariable Long guildId) {
        return service.readAll(guildId);
    }

    @GetMapping(path = "{guildId}/{name}")
    public ResponseEntity<Character> read(@PathVariable Long guildId, @PathVariable String name) {
        return service.read(guildId, name);
    }

    @PutMapping(path = "{guildId}/{name}")
    public ResponseEntity<String> update(@RequestBody Character room, @PathVariable Long guildId, @PathVariable String name) {
        return service.update(room, guildId, name);
    }

    @DeleteMapping(path="{guildId}/{name}")
    public ResponseEntity<String> delete(@PathVariable Long guildId, @PathVariable String name) {
        return service.delete(guildId, name);
    }

}
