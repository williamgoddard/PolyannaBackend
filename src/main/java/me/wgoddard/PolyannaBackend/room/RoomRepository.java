package me.wgoddard.PolyannaBackend.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByServerDiscordId(Long guildId);

    List<Room>  findByServerDiscordIdAndName(Long discordId, String name);

    Optional<Room> findByName(String name);

}
