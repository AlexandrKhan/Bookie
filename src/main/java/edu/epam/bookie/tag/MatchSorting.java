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

/**
 * Match sorting tags
 */
public class MatchSorting {

    private MatchSorting() {
    }

    /**
     * Only not started matches
     *
     * @param matches list of matches
     * @return filtered list of matches
     */
    public static List<Match> selectNotStartedMatches(List<Match> matches) {
        return matches.stream()
                .filter(m -> m.getMatchProgress().equals(MatchProgress.valueOf("NOT_STARTED")))
                .collect(Collectors.toList());
    }

    /**
     * Sort by date (descending order)
     *
     * @param matches list of matches
     * @return sorted list of matches
     */
    public static List<Match> sortByDateThenTime(List<Match> matches) {
        Comparator<Match> byDateThenTime = Comparator.comparing(Match::getStartDate);
        byDateThenTime = byDateThenTime.thenComparing(Match::getStartTime).reversed();
        matches.sort(byDateThenTime);
        return matches;
    }
}
