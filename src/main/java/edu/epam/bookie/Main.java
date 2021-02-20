package edu.epam.bookie;

import edu.epam.bookie.controller.scheduler.GetTodayMatchRunnable;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        /*GetTodayMatchRunnable getTodayMatchRunnable = new GetTodayMatchRunnable();
        getTodayMatchRunnable.run();*/

        System.out.println(LocalTime.now());
    }
}
