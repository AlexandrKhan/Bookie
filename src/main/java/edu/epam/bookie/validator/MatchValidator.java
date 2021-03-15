package edu.epam.bookie.validator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MatchValidator {

    private MatchValidator() {
    }

    public static boolean matchWithinAMonth(LocalDate date) {
        return LocalDate.now().plusMonths(1).isBefore(date);
    }

    public static boolean correctCoeff(BigDecimal coeff) {
        return coeff != null && coeff.compareTo(new BigDecimal(100)) < 0;
    }
}
