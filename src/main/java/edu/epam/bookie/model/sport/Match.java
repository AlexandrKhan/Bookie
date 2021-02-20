package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Match implements Entity {
    private int id;
    private Team firstTeam;
    private Team secondTeam;
    private int firstTeamGoals;
    private int secondTeamGoals;
    private LocalDate startDate;
    private LocalTime startTime;
    private Result result;
    private MatchProgress matchProgress;

    public Match() {
    }

    public Match(Team firstTeam, Team secondTeam, LocalDate startDate, LocalTime startTime) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstTeamGoals = 0;
        this.secondTeamGoals = 0;
        this.startDate = startDate;
        this.startTime = startTime;
        this.matchProgress = MatchProgress.NOT_STARTED;
    }

    public int getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public void setFirstTeamGoals(int firstTeamGoals) {
        this.firstTeamGoals = firstTeamGoals;
    }

    public int getSecondTeamGoals() {
        return secondTeamGoals;
    }

    public void setSecondTeamGoals(int secondTeamGoals) {
        this.secondTeamGoals = secondTeamGoals;
    }

    public MatchProgress getMatchProgress() {
        return matchProgress;
    }

    public void setMatchProgress(MatchProgress matchProgress) {
        this.matchProgress = matchProgress;
    }

    public void setMatchProgress(String matchProgress) {
        this.matchProgress = MatchProgress.valueOf(matchProgress);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = Team.valueOf(firstTeam);
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = Team.valueOf(secondTeam);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setResult(String result) {
        this.result = Result.valueOf(result);
    }
}
