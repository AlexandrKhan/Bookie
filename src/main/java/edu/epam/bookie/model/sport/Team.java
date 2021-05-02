package edu.epam.bookie.model.sport;

/**
 * Football teams
 */
public enum Team {
    MANCHESTER_CITY("Manchester City"),
    MANCHESTER_UNITED("Manchester United"),
    LEICESTER_CITY("Leicester City"),
    CHELSEA("Chelsea"),
    WEST_HAM_UNITED("West Ham United"),
    LIVERPOOL("Liverpool"),
    EVERTON("Everton"),
    ASTON_VILLA("Aston Villa"),
    TOTTENHAM_HOTSPUR("Tottenham Hotspur"),
    ARSENAL("Arsenal"),
    LEEDS_UNITED("Leeds United"),
    WOLVERHAMPTON_WANDERERS("Wolverhampton Wanderers"),
    SOUTHAMPTON("Southampton"),
    CRYSTAL_PALACE("Crystal Palace"),
    BURNLEY("Burnley"),
    BRIGHTON_AND_HOVE_ALBION("Brighton and Hove Albion"),
    NEWCASTLE_UNITED("Newcastle United"),
    FULHAM("Fulham"),
    WEST_BROMWICH_ALBION("West Bromwich Albion"),
    SHEFFIELD_UNITED("Sheffield United");

    private final String name;
    private static final String WHITESPACE = "\\s";
    private static final String UNDERLINE = "_";

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Team getValue(String name) {
        return Team.valueOf(name.toUpperCase().replaceAll(WHITESPACE, UNDERLINE));
    }

    @Override
    public String toString() {
        return "Team = " + name;
    }
}
