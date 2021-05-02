package edu.epam.bookie.validator;

import edu.epam.bookie.model.sport.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Validator for matches
 */
public class MatchValidator {

    private MatchValidator() {
    }

    /**
     * Check if the coefficients are valid
     *
     * @param home home coefficient
     * @param draw draw coefficient
     * @param away away coefficient
     * @return result
     */
    public static boolean correctCoeff(BigDecimal home, BigDecimal draw, BigDecimal away) {
        return home != null && home.compareTo(new BigDecimal(100)) < 0 && home.compareTo(new BigDecimal(0)) > 0 &&
        draw != null && draw.compareTo(new BigDecimal(100)) < 0 && draw.compareTo(new BigDecimal(0)) > 0 &&
        away != null && away.compareTo(new BigDecimal(100)) < 0 && away.compareTo(new BigDecimal(0)) > 0;
    }

    /**
     * Check if match date/time is valid
     * Can't have past date
     * And if the match is today time must be at least (now + 1 hour)
     *
     * @param date match date
     * @param time match time
     * @return result
     */
    public static boolean isValidTimeForMatchUpdate(LocalDate date, LocalTime time) {
        return date.isAfter(LocalDate.now()) ||
               (!date.isBefore(LocalDate.now()) && time.isAfter(LocalTime.now().plusMinutes(60)));
    }

    /**
     * Team can't play with themselves :)
     *
     * @param home home team
     * @param away away team
     * @return result
     */
    public static boolean areDifferentTeams(Team home, Team away) {
        return !home.equals(away);
    }
}
