package com.playsafeholdings.consoleroulette.player;

import org.springframework.stereotype.Component;

@Component
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void addPlayer(Player player) {
        playerRepository.save(player);
    }
}
