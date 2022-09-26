package me.wgoddard.PolyannaBackend.repos;

import me.wgoddard.PolyannaBackend.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    Optional<Server> findByDiscordId(Long discordId);

}
