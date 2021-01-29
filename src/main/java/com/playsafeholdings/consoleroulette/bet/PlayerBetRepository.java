package com.playsafeholdings.consoleroulette.bet;

import java.util.List;

public interface PlayerBetRepository {

    void addPlayerBet(PlayerBet playerBet);

    List<PlayerBet> findPlayerBets();

    void reset();
}
