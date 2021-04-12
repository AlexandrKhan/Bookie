package edu.epam.bookie.validator;

import edu.epam.bookie.model.sport.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class MatchValidator {

    private MatchValidator() {
    }

    public static boolean matchWithinAMonth(LocalDate date) {
        return LocalDate.now().plusMonths(1).isBefore(date);
    }

    public static boolean correctCoeff(BigDecimal coeff) {
        return coeff != null && coeff.compareTo(new BigDecimal(100)) < 0;
    }

    public static boolean isValidTimeForMatchUpdate(LocalDate date, LocalTime time) {
        return date.isAfter(LocalDate.now()) ||  (!date.isBefore(LocalDate.now()) && time.isAfter(LocalTime.now().plusMinutes(60)));
    }

    public static boolean areDifferentTeams(Team home, Team away) {
        return !home.equals(away);
    }
}
