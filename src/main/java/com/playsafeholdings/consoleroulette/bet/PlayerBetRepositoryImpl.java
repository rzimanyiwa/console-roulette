package com.playsafeholdings.consoleroulette.bet;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerBetRepositoryImpl implements PlayerBetRepository{

    private List<PlayerBet> playerBets = new ArrayList<>();

    @Override
    public void addPlayerBet(PlayerBet playerBet) {
        playerBets.add(playerBet);
    }

    @Override
    public List<PlayerBet> findPlayerBets() {
        return playerBets;
    }

    @Override
    public void reset() {
        playerBets.clear();
    }
}
