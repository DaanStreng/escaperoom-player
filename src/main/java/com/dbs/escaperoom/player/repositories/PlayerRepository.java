package com.dbs.escaperoom.player.repositories;
import com.dbs.escaperoom.player.models.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends CrudRepository<Player, String> {
    @Query("SELECT p FROM Player p WHERE p.username = ?1" )
    Player getPlayerByUsername(String username);

    @Query("SELECT p FROM Player p WHERE p.uuid = ?1" )
    Player getPlayer(String uuid);
}
