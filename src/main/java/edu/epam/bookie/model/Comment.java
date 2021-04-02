package edu.epam.bookie.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Comment implements Entity {
    private int id;
    private int matchId;
    private int userId;
    private String comment;
    private LocalDate date;
    private LocalTime time;

    public Comment() {
    }

    public Comment(int matchId, int userId, String comment) {
        this.matchId = matchId;
        this.userId = userId;
        this.comment = comment;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
