package edu.epam.bookie.model;

/**
 * Theme of message sent to user
 */
public enum Theme {
    WON("Won"),
    DELAY("Delay"),
    BAN("Ban"),
    UNBAN("Unban");

    String name;

    Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
