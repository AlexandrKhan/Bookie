package edu.epam.bookie.controller.scheduler;

import edu.epam.bookie.exception.MatchServiceException;
import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.MatchProgress;
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
        for (Map.Entry<Integer, LocalTime> entry: MatchServiceImpl.todayMatchStartTimeMap.entrySet()) {
            if (LocalTime.now().isAfter(entry.getValue())) {
                Integer id = entry.getKey();
                try {
                    matchService.setGoalsById(Long.valueOf(id));
                    matchService.setMatchProgressOver(Long.valueOf(id));
                    Match match = matchService.findById(Long.valueOf(id));
                    //MatchServiceImpl.todayMatchStartTimeMap.entrySet().removeIf(e -> match.getMatchProgress().equals(MatchProgress.OVER));
                    logger.info("Generated score for match id = {}", entry.getKey());
                } catch (MatchServiceException e) {
                    logger.error("Cant get match by id");
                }
                /*try {
                    matchService.setGoalsAndResultById(Long.valueOf(id));
                    matchService.setMatchProgressOver(Long.valueOf(id));
                    MatchServiceImpl.todayMatchStartTimeMap.entrySet().removeIf(e -> {
                        try {
                            return matchService.findById(Long.valueOf(id)).getMatchProgress().equals(MatchProgress.OVER);
                        } catch (MatchServiceException e1) {
                            logger.error("Error removing over match");
                        }
                    });
                    logger.info("Generated score for match id = {}", entry.getKey());
                } catch (MatchServiceException e) {
                    logger.error("Cant generate score" + e);
                }*/
            }
        }
    }
}
