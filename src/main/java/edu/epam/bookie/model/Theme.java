package edu.epam.bookie.model;

/**
 * Theme of message sent to user
 * Used for UI (color of message)
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
