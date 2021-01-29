package com.playsafeholdings.consoleroulette;

import com.playsafeholdings.consoleroulette.bet.BetService;
import com.playsafeholdings.consoleroulette.player.Player;
import com.playsafeholdings.consoleroulette.player.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ConsoleRouletteApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ConsoleRouletteApplication.class.getName());

    @Autowired
    private Environment environment;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BetService betService;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleRouletteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        fileToInputStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = "";
            while (!"q".equalsIgnoreCase(input)) {
                input = br.readLine();
                if (input.length() > 0) {
                    List<String> betArgs = Arrays.asList(input.split("\\s+"));
                    String playerName = betArgs.get(0);
                    String betNumber = betArgs.get(1);
                    BigDecimal amount = new BigDecimal(betArgs.get(2));
                    betService.addBet(playerName, betNumber, amount);
                }
            }
        } catch (IOException e) {
            logger.info("An error occurred: {}", e);
        }
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private void fileToInputStream() throws IOException {
        String fileName = environment.getRequiredProperty("input.file.name");
        InputStream inputStream = new ClassPathResource(fileName).getInputStream();
        String[] players = readFromInputStream(inputStream).split("\\n");
        for (String player : players) {
            List<String> playerParams = Arrays.asList(player.split("\\,"));
            Player newPlayer = new Player();
            newPlayer.setName(playerParams.get(0));
            newPlayer.setTotalBet(new BigDecimal(playerParams.get(1)));
            newPlayer.setTotalWin(new BigDecimal(playerParams.get(2)));
            playerService.addPlayer(newPlayer);
        }
    }
}
