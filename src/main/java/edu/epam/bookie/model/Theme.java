package edu.epam.bookie.model;

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
