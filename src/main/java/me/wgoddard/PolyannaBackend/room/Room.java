package me.wgoddard.PolyannaBackend.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.wgoddard.PolyannaBackend.inventory.Inventory;
import me.wgoddard.PolyannaBackend.server.Server;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @OneToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    private String name;

}
