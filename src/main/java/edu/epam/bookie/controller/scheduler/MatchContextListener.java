package edu.epam.bookie.controller.scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Schedules 2 tasks
 * 1) Get all today matches
 * 2) When match time == LocalTime.now() -> generate random score
 */

@WebListener
public class MatchContextListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;
    static Map<Integer, LocalTime> todayMatchStartTimeMap = new ConcurrentHashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(new GetTodayMatchTask(), 0, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new GenerateScoreTask(), 0, 15, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}
