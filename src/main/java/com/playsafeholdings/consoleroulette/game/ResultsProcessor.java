package com.playsafeholdings.consoleroulette.game;

import com.playsafeholdings.consoleroulette.bet.Bet;
import com.playsafeholdings.consoleroulette.bet.PlayerBet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
class ResultsProcessor {

    void processResults(int winningNumber, List<PlayerBet> playerBets) {
        playerBets.forEach(playerBet -> {
            Bet bet = playerBet.getBet();
            Optional<BigDecimal> winnings = bet.getWinnings(winningNumber);
            updateBetStatus(bet, winnings);
        });
    }

    private void updateBetStatus(Bet bet, Optional<BigDecimal> winnings) {
        bet.setWinnings(winnings.orElse(BigDecimal.ZERO));
        bet.setOutcome(winnings.map(amount -> Bet.Outcome.WIN).orElse(Bet.Outcome.LOSE));
    }
}
