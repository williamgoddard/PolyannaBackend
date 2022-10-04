package me.wgoddard.PolyannaBackend.repos;

import me.wgoddard.PolyannaBackend.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByIdAndServerDiscordId(Long id, Long guildId);

}
