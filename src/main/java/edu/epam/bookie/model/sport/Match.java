package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Match implements Entity {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private LocalDate startDate;
    private LocalTime startTime;
    private Result result;
    private MatchProgress matchProgress;
    private BigDecimal homeCoeff;
    private BigDecimal drawCoeff;
    private BigDecimal awayCoeff;

    public Match() {
    }

    public Match(Team homeTeam, Team awayTeam, LocalDate startDate, LocalTime startTime, BigDecimal homeCoeff, BigDecimal drawCoeff, BigDecimal awayCoeff) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startDate = startDate;
        this.startTime = startTime;
        this.homeCoeff = homeCoeff;
        this.drawCoeff = drawCoeff;
        this.awayCoeff = awayCoeff;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
        this.matchProgress = MatchProgress.NOT_STARTED;
        this.result = Result.DRAW;
    }


    public BigDecimal getHomeCoeff() {
        return homeCoeff;
    }

    public void setHomeCoeff(BigDecimal homeCoeff) {
        this.homeCoeff = homeCoeff;
    }

    public BigDecimal getDrawCoeff() {
        return drawCoeff;
    }

    public void setDrawCoeff(BigDecimal drawCoeff) {
        this.drawCoeff = drawCoeff;
    }

    public BigDecimal getAwayCoeff() {
        return awayCoeff;
    }

    public void setAwayCoeff(BigDecimal awayCoeff) {
        this.awayCoeff = awayCoeff;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int firstTeamGoals) {
        this.homeTeamGoals = firstTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.homeTeam = Team.valueOf(firstTeam);
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.awayTeam = Team.valueOf(secondTeam);
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
