package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Football match
 */
public class Match {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private LocalDate startDate;
    private LocalTime startTime;
    private Result result;
    private MatchProgress matchProgress;

    /**
     * homeCoeff, drawCoeff, awayCoeff represent the coefficients for bets on this match
     * ex. if homeCoeff is 3 and your bet is 100, win would be 300 (including the bet amount)
     */
    private BigDecimal homeCoeff;
    private BigDecimal drawCoeff;
    private BigDecimal awayCoeff;

    private Match(MatchBuilder builder) {
        this.id = builder.id;
        this.homeTeam = builder.homeTeam;
        this.awayTeam = builder.awayTeam;
        this.startDate = builder.startDate;
        this.startTime = builder.startTime;
        this.homeCoeff = builder.homeCoeff;
        this.drawCoeff = builder.drawCoeff;
        this.awayCoeff = builder.awayCoeff;
        this.homeTeamGoals = builder.homeTeamGoals;
        this.awayTeamGoals = builder.awayTeamGoals;
        this.matchProgress = builder.matchProgress;
        this.result = builder.result;
    }

    public static class MatchBuilder {
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

        public MatchBuilder() {
        }

        public MatchBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public MatchBuilder withHomeTeam(Team homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public MatchBuilder withAwayTeam(Team awayTeam) {
            this.awayTeam = awayTeam;
            return this;
        }

        public MatchBuilder withHomeGoals(int homeTeamGoals) {
            this.homeTeamGoals = homeTeamGoals;
            return this;
        }

        public MatchBuilder withAwayGoals(int awayTeamGoals) {
            this.awayTeamGoals = awayTeamGoals;
            return this;
        }

        public MatchBuilder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public MatchBuilder withStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public MatchBuilder withResult(Result result) {
            this.result = result;
            return this;
        }

        public MatchBuilder withProgress(MatchProgress matchProgress) {
            this.matchProgress = matchProgress;
            return this;
        }

        public MatchBuilder withHomeCoeff(BigDecimal homeCoeff) {
            this.homeCoeff = homeCoeff;
            return this;
        }

        public MatchBuilder withDrawCoeff(BigDecimal drawCoeff) {
            this.drawCoeff = drawCoeff;
            return this;
        }

        public MatchBuilder withAwayCoeff(BigDecimal awayCoeff) {
            this.awayCoeff = awayCoeff;
            return this;
        }

        public MatchBuilder withProgress(BigDecimal awayCoeff) {
            this.awayCoeff = awayCoeff;
            return this;
        }

        public MatchBuilder withNewMatchValues() {
            this.homeTeamGoals = 0;
            this.awayTeamGoals = 0;
            this.matchProgress = MatchProgress.NOT_STARTED;
            this.result = Result.DRAW;
            return this;
        }

        public Match build() {
            return new Match(this);
        }
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
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (matchProgress != null ? matchProgress.hashCode() : 0);
        result1 = 31 * result1 + (homeCoeff != null ? homeCoeff.hashCode() : 0);
        result1 = 31 * result1 + (drawCoeff != null ? drawCoeff.hashCode() : 0);
        result1 = 31 * result1 + (awayCoeff != null ? awayCoeff.hashCode() : 0);
        return result1;
    }
}
