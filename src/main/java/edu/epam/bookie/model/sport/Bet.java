package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;
import edu.epam.bookie.model.StatusType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * User bet on match
 */
public class Bet implements Entity {
    private int id;
    private int userId;
    private int matchId;
    private LocalDate betDate;
    private LocalTime betTime;
    private BigDecimal betAmount;
    private Result betOnResult;
    private BetStatus betStatus;
    private BigDecimal betCoeff;

    public Bet() {
    }

    public Bet(int userId, int matchId, BigDecimal betAmount, Result betOnResult) {
        this.userId = userId;
        this.matchId = matchId;
        this.betDate = LocalDate.now();
        this.betTime = LocalTime.now();
        this.betAmount = betAmount;
        this.betOnResult = betOnResult;
        this.betStatus = BetStatus.NOT_STARTED;
    }

    public BigDecimal getBetCoeff() {
        return betCoeff;
    }

    public void setBetCoeff(BigDecimal betCoeff) {
        this.betCoeff = betCoeff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public LocalDate getBetDate() {
        return betDate;
    }

    public void setBetDate(LocalDate betDate) {
        this.betDate = betDate;
    }

    public LocalTime getBetTime() {
        return betTime;
    }

    public void setBetTime(LocalTime betTime) {
        this.betTime = betTime;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public Result getBetOnResult() {
        return betOnResult;
    }

    public void setBetOnResult(Result betOnResult) {
        this.betOnResult = betOnResult;
    }
    public void setBetOnResult(String betOnResult) {
        this.betOnResult = Result.valueOf(betOnResult);
    }

    public BetStatus getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(BetStatus status) {
        this.betStatus = status;
    }
    public void setBetStatus(String status) {
        this.betStatus = BetStatus.valueOf(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (id != bet.id) return false;
        if (userId != bet.userId) return false;
        if (matchId != bet.matchId) return false;
        if (betDate != null ? !betDate.equals(bet.betDate) : bet.betDate != null) return false;
        if (betTime != null ? !betTime.equals(bet.betTime) : bet.betTime != null) return false;
        if (betAmount != null ? !betAmount.equals(bet.betAmount) : bet.betAmount != null) return false;
        if (betOnResult != bet.betOnResult) return false;
        if (betStatus != bet.betStatus) return false;
        return betCoeff != null ? betCoeff.equals(bet.betCoeff) : bet.betCoeff == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + matchId;
        result = 31 * result + (betDate != null ? betDate.hashCode() : 0);
        result = 31 * result + (betTime != null ? betTime.hashCode() : 0);
        result = 31 * result + (betAmount != null ? betAmount.hashCode() : 0);
        result = 31 * result + (betOnResult != null ? betOnResult.hashCode() : 0);
        result = 31 * result + (betStatus != null ? betStatus.hashCode() : 0);
        result = 31 * result + (betCoeff != null ? betCoeff.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", userId=" + userId +
                ", matchId=" + matchId +
                ", betDate=" + betDate +
                ", betTime=" + betTime +
                ", betAmount=" + betAmount +
                ", betOnResult=" + betOnResult +
                ", betStatus=" + betStatus +
                ", betCoeff=" + betCoeff +
                '}';
    }
}
