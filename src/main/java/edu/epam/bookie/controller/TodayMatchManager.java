package edu.epam.bookie.controller;

import edu.epam.bookie.controller.scheduler.GenerateScoreRunnable;
import edu.epam.bookie.controller.scheduler.GetTodayMatchRunnable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class TodayMatchManager implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new GetTodayMatchRunnable(), 0, 10, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new GenerateScoreRunnable(), 0, 10, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}
