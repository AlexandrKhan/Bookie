package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetTodayMatchRunnable implements Runnable {
    private static final Logger logger = LogManager.getLogger(GetTodayMatchRunnable.class);
    private static final MatchServiceImpl service = MatchServiceImpl.INSTANCE;


    @Override
    public void run() {
        try {
            List<Match> matchList = service.findAll();
            for (Match match : matchList) {
                if (match.getStartDate().isEqual(LocalDate.now())) {
                    if (!MatchServiceImpl.todayMatchStartTimeMap.containsKey(match.getId())) {
                        MatchServiceImpl.todayMatchStartTimeMap.put(match.getId(), match.getStartTime());
                        logger.info("Added match with id {} to todayMap", match.getId());
                        for (Map.Entry entry: MatchServiceImpl.todayMatchStartTimeMap.entrySet()) {
                            logger.info(entry.getKey() + " " + entry.getValue());
                        }
                    }
                }
            }
        } catch (MatchServiceException e) {
            logger.error("Error finding all matches" + e);
        }
    }
}
