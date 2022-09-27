package me.wgoddard.PolyannaBackend.repos;

import me.wgoddard.PolyannaBackend.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByServerDiscordId(Long guildId);

    List<Room>  findByServerDiscordIdAndName(Long discordId, String name);

    Optional<Room> findByName(String name);

}
