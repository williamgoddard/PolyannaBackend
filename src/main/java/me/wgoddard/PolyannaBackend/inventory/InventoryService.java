package me.wgoddard.PolyannaBackend.inventory;

import me.wgoddard.PolyannaBackend.server.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryService {

    private final InventoryRepository repo;

    private final ServerService serverService;

    @Autowired
    public InventoryService(InventoryRepository repo, ServerService serverService) {
        this.repo = repo;
        this.serverService = serverService;
    }

    public ResponseEntity<String> save(Inventory inventory, Long guildId) {
        inventory.setServer(serverService.read(guildId));
        repo.saveAndFlush(inventory);
        return new ResponseEntity<>("The inventory was saved successfully", HttpStatus.OK);
    }

    public ResponseEntity<Inventory> create(Long guildId) {
        Inventory inventory = new Inventory();
        this.save(inventory, guildId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    public ResponseEntity<Inventory> read(Long guildId, Long id) {
        Optional<Inventory> inventory = repo.findByIdAndServerDiscordId(id, guildId);
        if (inventory.isPresent()) {
            return new ResponseEntity<>(inventory.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> delete(Long guildId, Long id) {
        Optional<Inventory> inventory = repo.findByIdAndServerDiscordId(id, guildId);
        if (inventory.isPresent()) {
            repo.delete(inventory.get());
            return new ResponseEntity<>("The inventory was deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("The inventory could not be found", HttpStatus.BAD_REQUEST);
    }

}
