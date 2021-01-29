package com.playsafeholdings.consoleroulette.player;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlayerRepositoryImpl implements PlayerRepository{

    private List<Player> players = new ArrayList<>();

    @Override
    public Player save(Player player) {
        players.add(player);
        return player;
    }

    @Override
    public Optional<Player> findPlayerByName(String name) {
        return players.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst();
    }
}