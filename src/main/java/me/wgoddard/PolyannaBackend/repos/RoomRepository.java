package me.wgoddard.PolyannaBackend.repos;

import me.wgoddard.PolyannaBackend.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
