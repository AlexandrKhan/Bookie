package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;


public class GenerateScoreRunnable implements Runnable {
    private static final Logger logger = LogManager.getLogger(GenerateScoreRunnable.class);
    private MatchServiceImpl matchService = MatchServiceImpl.INSTANCE;

    @Override
    public void run() {
        MatchServiceImpl.todayMatchStartTimeMap.forEach((key, value) -> {
            if (LocalTime.now().isAfter(value)) {
                try {
                    matchService.setGoalsById(Long.valueOf(key));
                    matchService.setMatchProgressOver(Long.valueOf(key));
                    logger.info("Generated score for match id = {}", key);
                    MatchServiceImpl.todayMatchStartTimeMap.remove(key);
                } catch (MatchServiceException e) {
                    logger.error("Can't generate score: " + e);
                }
            }
        });
    }
}
