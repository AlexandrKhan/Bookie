package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.BetServiceImpl;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.List;

import static edu.epam.bookie.controller.scheduler.MatchContextListener.todayMatchStartTimeMap;

public class GenerateScoreTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(GenerateScoreTask.class);
    private MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private BetServiceImpl betService = BetServiceImpl.betService;

    @Override
    public void run() {
        todayMatchStartTimeMap.forEach((key, value) -> {
            if (LocalTime.now().isAfter(value)) {
                try {
                    matchService.setGoalsResultAndOverMatchById(Long.valueOf(key));
                    logger.info("Generated score for match id = {}", key);
                    Match match = matchService.findById(Long.valueOf(key));

                    List<Bet> matchBets = betService.selectBetsByMatchId(key);
                    for (Bet bet : matchBets) {
                        if (bet.getBetOnResult() == (match.getResult())) {
                            betService.payBets(bet);
                        } else {
                            betService.betLost(bet);
                        }
                    }

                    todayMatchStartTimeMap.remove(key);
                } catch (MatchServiceException e) {
                    logger.error("Can't generate score", e);
                } catch (BetServiceException e) {
                    logger.error("Bet service ex", e);
                }
            }
        });
    }
}
