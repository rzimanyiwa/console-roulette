package com.playsafeholdings.consoleroulette.bet;

import com.playsafeholdings.consoleroulette.player.Player;

public class PlayerBet {

    private final Player player;
    private final Bet bet;

    public PlayerBet(Player player, Bet bet) {
        this.player = player;
        this.bet = bet;
    }

    public Player getPlayer() {
        return player;
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public String toString() {
        return "PlayerBet{" +
                "player=" + player +
                ", bet=" + bet +
                '}';
    }
}
