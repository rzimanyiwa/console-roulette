package com.playsafeholdings.consoleroulette.game;

import com.playsafeholdings.consoleroulette.bet.PlayerBet;
import com.playsafeholdings.consoleroulette.bet.PlayerBetRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
class GameService {

    private PlayerBetRepository playerBetRepository;
    private ResultsProcessor resultsProcessor;
    private ResultsPublisher resultsPublisher;

    public GameService(PlayerBetRepository playerBetRepository,
                       ResultsProcessor resultsProcessor,
                       ResultsPublisher resultsPublisher) {
        this.playerBetRepository = playerBetRepository;
        this.resultsProcessor = resultsProcessor;
        this.resultsPublisher = resultsPublisher;
    }

    @Scheduled(cron = "*/30 * * * * *")
    void landBall() {
        int winningNumber = nextWinningNumber();
        List<PlayerBet> playerBets = playerBetRepository.findPlayerBets();
        resultsProcessor.processResults(winningNumber, playerBets);
        resultsPublisher.publishCurrentGameResults(winningNumber, playerBets);
        playerBetRepository.reset();
    }

    private int nextWinningNumber() {
        return new Random().nextInt(36-1) + 1;
    }
}

