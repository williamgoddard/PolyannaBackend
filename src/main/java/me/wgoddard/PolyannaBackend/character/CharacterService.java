package me.wgoddard.PolyannaBackend.character;

import me.wgoddard.PolyannaBackend.server.ServerService;
import me.wgoddard.PolyannaBackend.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CharacterService {

    private final CharacterRepository repo;

    private final ServerService serverService;
    private final InventoryService inventoryService;

    @Autowired
    public CharacterService(CharacterRepository repo, ServerService serverService, InventoryService inventoryService) {
        this.repo = repo;
        this.serverService = serverService;
        this.inventoryService = inventoryService;
    }

    public ResponseEntity<String> save(Character character, Long guildId) {
        character.setServer(serverService.read(guildId));
        character.setInventory(inventoryService.create(guildId).getBody());
        repo.saveAndFlush(character);
        return new ResponseEntity<>("The room was saved successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Character>> readAll(Long guildId) {
        return new ResponseEntity<>(repo.findByServerDiscordId(guildId), HttpStatus.OK);
    }

    public ResponseEntity<Character> read(Long guildId, String name) {
        Optional<Character> character = repo.findByServerDiscordIdAndName(guildId, name);
        if (character.isPresent()) {
            return new ResponseEntity<>(character.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> update(Character character, Long guildId, String name) {
        Optional<Character> newCharacter = repo.findByServerDiscordIdAndName(guildId, name);
        if (newCharacter.isPresent()) {
            newCharacter.get().setId(newCharacter.get().getId());
            return save(character, guildId);
        }
        return new ResponseEntity<>("The room could not be found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> delete(Long guildId, String name) {
        Optional<Character> character = repo.findByServerDiscordIdAndName(guildId, name);
        if (character.isPresent()) {
            repo.delete(character.get());
            return new ResponseEntity<>("The room was deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("The room could not be found", HttpStatus.BAD_REQUEST);
    }

}
