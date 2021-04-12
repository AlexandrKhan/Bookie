package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Football match
 */
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

    public Match(int id, Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, LocalDate startDate, LocalTime startTime, Result result) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.startDate = startDate;
        this.startTime = startTime;
        this.result = result;
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

    /**
     * homeCoeff, drawCoeff, awayCoeff represent the coefficients for bets on this match
     * ex. if homeCoeff is 3 and your bet is 100, potential win would be 300 (including the bet amount)
     *
     * @return coefficient for match
     */
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

    /**
     * Goals scored by home team
     *
     * @return goals
     */
    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int firstTeamGoals) {
        this.homeTeamGoals = firstTeamGoals;
    }
    /**
     * Goals scored by away team
     *
     * @return goals
     */
    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
    /**
     * Shows whether the match is started/over
     *
     * @return progress
     */
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
    /**
     * Result of the match (home win, draw, away win)
     *
     * @return result
     */
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setResult(String result) {
        this.result = Result.valueOf(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (id != match.id) return false;
        if (homeTeamGoals != match.homeTeamGoals) return false;
        if (awayTeamGoals != match.awayTeamGoals) return false;
        if (homeTeam != match.homeTeam) return false;
        if (awayTeam != match.awayTeam) return false;
        if (startDate != null ? !startDate.equals(match.startDate) : match.startDate != null) return false;
        if (startTime != null ? !startTime.equals(match.startTime) : match.startTime != null) return false;
        if (result != match.result) return false;
        if (matchProgress != match.matchProgress) return false;
        if (homeCoeff != null ? !homeCoeff.equals(match.homeCoeff) : match.homeCoeff != null) return false;
        if (drawCoeff != null ? !drawCoeff.equals(match.drawCoeff) : match.drawCoeff != null) return false;
        return awayCoeff != null ? awayCoeff.equals(match.awayCoeff) : match.awayCoeff == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (homeTeam != null ? homeTeam.hashCode() : 0);
        result1 = 31 * result1 + (awayTeam != null ? awayTeam.hashCode() : 0);
        result1 = 31 * result1 + homeTeamGoals;
        result1 = 31 * result1 + awayTeamGoals;
        result1 = 31 * result1 + (startDate != null ? startDate.hashCode() : 0);
        result1 = 31 * result1 + (startTime != null ? startTime.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (matchProgress != null ? matchProgress.hashCode() : 0);
        result1 = 31 * result1 + (homeCoeff != null ? homeCoeff.hashCode() : 0);
        result1 = 31 * result1 + (drawCoeff != null ? drawCoeff.hashCode() : 0);
        result1 = 31 * result1 + (awayCoeff != null ? awayCoeff.hashCode() : 0);
        return result1;
    }
}
