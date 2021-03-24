package edu.epam.bookie.tag;

import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchSorting {

    private MatchSorting() {
    }

    public static List<Match> sortByTeam(List<Match> matches, Team team) {
        List<Match> teamMatches;
        teamMatches = matches.stream()
                .filter(m -> m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team))
                .collect(Collectors.toList());
        return teamMatches;
    }

    public static List<Match> sortByHomeTeam(List<Match> matches) {
        Comparator<Match> byHomeTeam = Comparator.comparing((m) -> (m.getHomeTeam().getName()));
        matches.sort(byHomeTeam);
        return matches;
    }

    public static List<Match> sortByAwayTeam(List<Match> matches) {
        Comparator<Match> byAwayTeam = Comparator.comparing((m) -> (m.getAwayTeam().getName()));
        matches.sort(byAwayTeam);
        return matches;
    }

    public static List<Match> sortByDateThenTime(List<Match> matches) {
        Comparator<Match> byDateThenTime = Comparator.comparing((m) -> (m.getStartDate()));
        byDateThenTime = byDateThenTime.thenComparing(Comparator.comparing((m) -> (m.getStartTime())));
        matches.sort(byDateThenTime);
        return matches;
    }
}
