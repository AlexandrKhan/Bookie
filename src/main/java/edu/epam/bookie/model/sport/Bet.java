package edu.epam.bookie.model.sport;

import edu.epam.bookie.model.Entity;
import edu.epam.bookie.model.StatusType;

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
}
