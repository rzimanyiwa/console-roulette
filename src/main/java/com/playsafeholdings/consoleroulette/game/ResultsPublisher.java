package com.playsafeholdings.consoleroulette.game;

import com.playsafeholdings.consoleroulette.bet.PlayerBet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ResultsPublisher {

    public void publishCurrentGameResults(int winningNumber, List<PlayerBet> playerBets) {
        StringBuilder resultBuilder = new StringBuilder("Number: ");
        resultBuilder.append(winningNumber);
        resultBuilder.append("\n");
        resultBuilder.append("Player\t");
        resultBuilder.append("Bet\t");
        resultBuilder.append("Outcome\t");
        resultBuilder.append("Winnings\t");
        resultBuilder.append(buildFinalBetsString(playerBets));
        System.out.println(resultBuilder.toString());
    }

    String buildFinalBetsString(List<PlayerBet> betResults) {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (PlayerBet playerBet: betResults) {
            stringBuilder.append(playerBet.getPlayer().getName());
            stringBuilder.append("\t");
            stringBuilder.append(playerBet.getBet().getBetNumber());
            stringBuilder.append("\t");
            stringBuilder.append(playerBet.getBet().getOutcome());
            stringBuilder.append("\t");
            stringBuilder.append(playerBet.getBet().getWinnings());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
