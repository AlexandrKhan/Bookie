package edu.epam.bookie.util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurseWordsSet {
    public static final Set<String> curseWords = Stream.of("ass", "fool")
            .collect(Collectors.toSet());

    private CurseWordsSet() {
    }
}
