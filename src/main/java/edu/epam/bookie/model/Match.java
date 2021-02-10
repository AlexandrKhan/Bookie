package edu.epam.bookie.model;

import java.time.LocalDateTime;

public class Match implements Entity {
    private Sport sport;
    private Team first_team;
    private Team second_team;
    private LocalDateTime start_time;
    private BetType result;

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Team getFirst_team() {
        return first_team;
    }

    public void setFirst_team(Team first_team) {
        this.first_team = first_team;
    }

    public Team getSecond_team() {
        return second_team;
    }

    public void setSecond_team(Team second_team) {
        this.second_team = second_team;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public BetType getResult() {
        return result;
    }

    public void setResult(BetType result) {
        this.result = result;
    }
}
