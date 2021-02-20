package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.service.impl.MatchServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.Map;

public class GenerateScoreRunnable implements Runnable {
    private static final Logger logger = LogManager.getLogger(GenerateScoreRunnable.class);
    private MatchServiceImpl matchService = MatchServiceImpl.INSTANCE;

    @Override
    public void run() {
        for (Map.Entry<Integer, LocalTime> entry: MatchServiceImpl.todayMatchStartTimeMap.entrySet())
        if (LocalTime.now().isAfter(entry.getValue())) {
            try {
                matchService.setGoalsById(Long.valueOf(entry.getKey()));
                logger.info("Generated score for match id = {}", entry.getKey());
            } catch (MatchServiceException e) {
                logger.error("Cant generate score" + e);
            }
        }
    }
}
