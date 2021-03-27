package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.Message;
import edu.epam.bookie.model.Theme;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.BetStatus;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.BetServiceImpl;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.List;

import static edu.epam.bookie.controller.scheduler.MatchContextListener.todayMatchStartTimeMap;

@SuppressWarnings("DanglingJavadoc")
public class GenerateScoreTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(GenerateScoreTask.class);
    private MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private BetServiceImpl betService = BetServiceImpl.betService;
    private UserServiceImpl userService = UserServiceImpl.userService;

    /**
     * Generate score, end match and notify user
     */
    @Override
    public void run() {
        todayMatchStartTimeMap.forEach((matchId, startTime) -> {
            if (LocalTime.now().isAfter(startTime)) {
                try {
                    matchService.setGoalsResultAndOverMatchById(Long.valueOf(matchId));
                    logger.info("Generated score for match id = {}", matchId);
                    Match match = matchService.findById(Long.valueOf(matchId));
                    List<Bet> matchBets = betService.selectBetsByMatchId(matchId);
                    matchBets.stream()
                            .filter(b -> b.getBetStatus() == BetStatus.NOT_STARTED)
                            .forEach(b -> {
                                try {
                                    if (b.getBetOnResult() == match.getResult()) {
                                        betService.payBets(b);
                                        userService.addMessage(new Message(b.getUserId(), "Your bet on match: "
                                                + match.getHomeTeam().getName() +" - "
                                                + match.getAwayTeam().getName()
                                                + " has won!", Theme.WON));
                                    } else {
                                        betService.betLost(b);
                                    }
                                } catch (BetServiceException | UserServiceException e) {
                                    e.printStackTrace();
                                }
                            });
                    todayMatchStartTimeMap.remove(matchId);
                } catch (MatchServiceException| BetServiceException e) {
                    logger.error("Match service ex", e);
                }
            }
        });
    }
}
