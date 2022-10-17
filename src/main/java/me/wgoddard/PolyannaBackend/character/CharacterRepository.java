package me.wgoddard.PolyannaBackend.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByServerDiscordId(Long guildId);

    Optional<Character>  findByServerDiscordIdAndName(Long discordId, String name);

    Optional<Character> findByName(String name);

}
