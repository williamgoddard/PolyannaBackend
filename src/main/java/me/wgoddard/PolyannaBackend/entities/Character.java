package me.wgoddard.PolyannaBackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Character {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @OneToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Column(unique = true)
    private String name;

}
