package com.playsafeholdings.consoleroulette.player;

import java.util.Optional;

public interface PlayerRepository {

    Player save(Player player);

    Optional<Player> findPlayerByName(String name);
}
