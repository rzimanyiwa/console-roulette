package com.playsafeholdings.consoleroulette.bet;

import com.playsafeholdings.consoleroulette.exception.GameException;

import java.math.BigDecimal;
import java.util.Optional;

public class Bet {

    private String betNumber;
    private BigDecimal betAmount;
    private BigDecimal winnings;
    private Outcome outcome;

    public Bet(String betNumber, BigDecimal betAmount) {
        this.betNumber = betNumber;
        this.betAmount = betAmount;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public BigDecimal getWinnings() {
        return winnings;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setWinnings(BigDecimal winnings) {
        this.winnings = winnings;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Optional<BigDecimal> getWinnings(int number) {
        switch (determineType(betNumber)) {
            case ODD:
                return multiplyWinnings(Optional.of(betAmount).filter(amount -> number % 2 != 0), BigDecimal.valueOf(2));
            case EVEN:
                return multiplyWinnings(Optional.of(betAmount).filter(amount -> number % 2 == 0), BigDecimal.valueOf(2));
            case NUMBER:
                if (betNumber.equalsIgnoreCase(String.valueOf(number)))
                    return Optional.ofNullable(betAmount.multiply(new BigDecimal(30)));
            default:
                return Optional.empty();
        }
    }

    private Type determineType(String betNumber) {
        String regex = "\\d+";
        if (betNumber.matches(regex))
            return Type.NUMBER;
        else if (betNumber.equalsIgnoreCase("ODD"))
            return Type.ODD;
        else if (betNumber.equalsIgnoreCase("EVEN"))
            return Type.EVEN;
        else
            throw new GameException();
    }

    private Optional<BigDecimal> multiplyWinnings(Optional<BigDecimal> winnings, BigDecimal multiplier) {
        return winnings.map(amount -> amount.multiply(multiplier));
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public enum Type {
        NUMBER, ODD, EVEN
    }

    public enum Outcome {
        LOSE, WIN
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betNumber='" + betNumber + '\'' +
                ", betAmount=" + betAmount +
                ", winnings=" + winnings +
                ", outcome=" + outcome +
                '}';
    }
}
