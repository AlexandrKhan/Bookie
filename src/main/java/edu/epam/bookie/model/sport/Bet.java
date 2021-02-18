package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;

public class Bet implements Entity {
    private long user_id;
    private long match_id;
    private Result result;
    private double betAmount;

}
