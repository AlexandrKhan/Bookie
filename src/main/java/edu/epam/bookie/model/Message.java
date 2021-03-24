package edu.epam.bookie.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message implements Entity{
    private int id;
    private int userId;
    private LocalDate date;
    private LocalTime time;
    private String message;

    public Message(int userId, String message) {
        this.userId = userId;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.message = message;
    }

    public Message() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != message1.id) return false;
        if (userId != message1.userId) return false;
        if (date != null ? !date.equals(message1.date) : message1.date != null) return false;
        if (time != null ? !time.equals(message1.time) : message1.time != null) return false;
        return message != null ? message.equals(message1.message) : message1.message == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
