package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bet implements Entity {
    private int id;
    private int userId;
    private int matchId;
    private LocalDate betDate;
    private LocalTime betTime;
    private BigDecimal betAmount;
    private Result betOnResult;

    public Bet(int userId, int matchId, LocalDate betDate, LocalTime betTime, BigDecimal betAmount, Result betOnResult) {
        this.userId = userId;
        this.matchId = matchId;
        this.betDate = betDate;
        this.betTime = betTime;
        this.betAmount = betAmount;
        this.betOnResult = betOnResult;
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
}
