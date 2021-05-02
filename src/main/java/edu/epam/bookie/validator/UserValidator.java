package edu.epam.bookie.validator;

import java.time.LocalDate;

public class UserValidator {
    private static final String USERNAME_REGEX = "[a-zA-Z0-9]{5,20}";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9@#$%!]{8,20}";
    private static final String EMAIL_REGEX = "([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}";
    private static final String NAME_REGEX = "^[a-zA-Z\\s]+";

    private static final ValidationErrorSet errorSet = ValidationErrorSet.getInstance();

    private UserValidator() {
    }

    public static boolean isUsername(String username) {
        return username != null && username.matches(USERNAME_REGEX);
    }

    public static boolean isPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public static boolean isName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    /**
     * User must be 18 to register
     *
     * @param date birth date
     * @return result
     */
    public static boolean legalAge(LocalDate date) {
        return date != null && date.plusYears(18).isAfter(LocalDate.now());
    }

    /**
     * When user registrates he must input password twice
     * Check if they match
     *
     * @param pass password
     * @param repeat password
     * @return result
     */
    public static boolean passwordsMatch(String pass, String repeat) {
        return pass.equals(repeat);
    }
}
