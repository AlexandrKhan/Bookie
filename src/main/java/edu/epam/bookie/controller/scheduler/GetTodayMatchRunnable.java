package edu.epam.bookie.controller.scheduler;

import static edu.epam.bookie.controller.scheduler.MatchManager.todayMatchStartTimeMap;
import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.MatchProgress;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class GetTodayMatchRunnable implements Runnable {
    private static final Logger logger = LogManager.getLogger(GetTodayMatchRunnable.class);
    private static final MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public void run() {
        try {
            List<Match> matchList = service.findAll();

            for (Match match : matchList) {
                if (checkIfMatchIsTodayAndNotOver(match)) {
                    if (!todayMatchStartTimeMap.containsKey(match.getId())) {
                        todayMatchStartTimeMap.put(match.getId(), match.getStartTime());
                        logger.info("Added match with id {} to todayMap, start time at: {}", match.getId(), match.getStartTime());
                    }
                }
            }
        } catch (MatchServiceException e) {
            logger.error("Error finding today matches: " + e);
        }
    }

    private boolean checkIfMatchIsTodayAndNotOver(Match match) {
        return (!match.getMatchProgress().equals(MatchProgress.valueOf("OVER"))
                && match.getStartDate().isEqual(LocalDate.now()));
    }
}
