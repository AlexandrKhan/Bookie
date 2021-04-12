package edu.epam.bookie.tag;

import edu.epam.bookie.model.sport.Match;
import edu.epam.bookie.model.sport.MatchProgress;
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

    public static List<Match> selectNotStartedMatches(List<Match> matches) {
        return matches.stream()
                .filter(m -> m.getMatchProgress().equals(MatchProgress.valueOf("NOT_STARTED")))
                .collect(Collectors.toList());
    }

    public static List<Match> sortByDateThenTime(List<Match> matches) {
        Comparator<Match> byDateThenTime = Comparator.comparing((m) -> (m.getStartDate()));
        byDateThenTime = byDateThenTime.thenComparing(Comparator.comparing((m) -> (m.getStartTime()))).reversed();
        matches.sort(byDateThenTime);
        return matches;
    }
}
