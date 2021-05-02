package edu.epam.bookie.model.sport;

/**
 * Result of the match
 */
public enum Result {
    HOME("Home"),
    DRAW("Draw"),
    AWAY("Away");
    private final String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Result = " + name;
    }
}


