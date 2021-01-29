package com.playsafeholdings.consoleroulette.bet;

import java.math.BigDecimal;

public interface BetService {

    void addBet(String playerName, String betNumber, BigDecimal amount);

}
