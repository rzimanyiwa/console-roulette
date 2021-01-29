package com.playsafeholdings.consoleroulette.bet;

import com.playsafeholdings.consoleroulette.exception.GameException;
import com.playsafeholdings.consoleroulette.player.Player;
import com.playsafeholdings.consoleroulette.player.PlayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BetServiceImpl implements BetService {

    private PlayerRepository playerRepository;
    private PlayerBetRepository playerBetRepository;

    public BetServiceImpl(PlayerRepository playerRepository, PlayerBetRepository playerBetRepository) {
        this.playerRepository = playerRepository;
        this.playerBetRepository = playerBetRepository;
    }

    @Override
    public void addBet(String playerName, String betNumber, BigDecimal amount) {
        Player player = playerRepository.findPlayerByName(playerName).orElseThrow(() -> new GameException());
        Bet bet = new Bet(betNumber, amount);
        PlayerBet playerBet = new PlayerBet(player, bet);
        playerBetRepository.addPlayerBet(playerBet);
    }
}
