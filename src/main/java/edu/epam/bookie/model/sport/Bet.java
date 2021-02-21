package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Bet implements Entity {
    private int id;
    private int user_id;
    private int match_id;
    private Result betOnResult;
    private double betAmount;
    private LocalDate bet_date;
    private LocalTime bet_time;
}
