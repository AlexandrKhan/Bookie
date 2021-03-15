package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.BetServiceException;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.exception.UserServiceException;
import edu.epam.bookie.model.User;
import edu.epam.bookie.model.sport.Bet;
import edu.epam.bookie.model.sport.BetStatus;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.BetServiceImpl;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import edu.epam.bookie.service.impl.UserServiceImpl;
import edu.epam.bookie.util.mail.MailUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static edu.epam.bookie.controller.scheduler.MatchContextListener.todayMatchStartTimeMap;

@SuppressWarnings("DanglingJavadoc")
public class GenerateScoreTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(GenerateScoreTask.class);
    private MatchServiceImpl matchService = MatchServiceImpl.matchService;
    private BetServiceImpl betService = BetServiceImpl.betService;
    private UserServiceImpl userService = UserServiceImpl.userService;

    @Override
    public void run() {
        todayMatchStartTimeMap.forEach((key, value) -> {
            if (LocalTime.now().isAfter(value)) {
                try {

/**
 Generates score and ends match
 */
                    matchService.setGoalsResultAndOverMatchById(Long.valueOf(key));
                    logger.info("Generated score for match id = {}", key);
                    Match match = matchService.findById(Long.valueOf(key));
                    List<Bet> matchBets = betService.selectBetsByMatchId(key);
                    for (Bet bet : matchBets) {
                        String email = "";
                        try {
                            Optional<String> emailTemp = userService.findEmailById(String.valueOf(bet.getUserId()));
                            if (emailTemp.isPresent()) {
                                email = emailTemp.get();
                            }
                        } catch (UserServiceException e) {
                            logger.error(e);
                        }
                        if (bet.getBetStatus() == BetStatus.NOT_STARTED){
                            if (bet.getBetOnResult() == (match.getResult())) {
                                betService.payBets(bet);
                                MailUtility.sendMessage(email, "Your bet won!", "Check your balance for your bet pay");
                            } else {
                                betService.betLost(bet);
                            }
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
