package edu.epam.bookie.controller.scheduler;

import static edu.epam.bookie.controller.scheduler.MatchContextListener.todayMatchStartTimeMap;

import edu.epam.bookie.exception.ServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.MatchProgress;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Collect today matches to map (todayMatchStartTimeMap)
 * Check if any match time was updated (line 34) -> write new time
 */
public class GetTodayMatchTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(GetTodayMatchTask.class);
    private final MatchServiceImpl service = MatchServiceImpl.matchService;

    @Override
    public void run() {
        try {
            List<Match> matchList = service.findMatchesOfDate(LocalDate.now());
            matchList.forEach(m -> {
                int id = m.getId();
                LocalTime time = m.getStartTime();

                if (matchTodayAndNotOver(m)) {
                    if (todayMatchStartTimeMap.containsKey(id)) {
                        if (todayMatchStartTimeMap.get(id).compareTo(time) != 0) {
                            todayMatchStartTimeMap.put(id, time);
                            logger.info("Match with id {} was updated, new start time at: {}", id, time);
                        }
                    } else {
                        todayMatchStartTimeMap.put(id, time);
                        logger.info("Added match with id {} to todayMap, start time at: {}", id, time);
                    }
                }
            });
        } catch (ServiceException e) {
            logger.error("Error finding today matches: " + e);
        }
    }

    /**
     * Check that match in list is today
     *
     * @param match match
     * @return boolean
     */
    private boolean matchTodayAndNotOver(Match match) {
        return (match.getMatchProgress().equals(MatchProgress.valueOf("NOT_STARTED"))
                && match.getStartDate().isEqual(LocalDate.now()));
    }
}
