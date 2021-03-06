package edu.epam.bookie.validator;

/**
 * Errors in input forms (for UI)
 */
public enum ValidationError {
    LOGIN_EXISTS,
    EMAIL_EXISTS,
    BAD_LOGIN_FORMAT,
    BAD_PASSWORD_FORMAT,
    BAD_EMAIL_FORMAT,
    WRONG_LOGIN_OR_PASSWORD,
    WRONG_LOGIN,
    WRONG_PASSWORD,
    ILLEGAL_AGE,
    BAD_DATE_FOR_MATCH,
    PASSWORDS_DONT_MATCH,
    NOT_ENOUGH_MONEY,
    TWO_SAME_TEAMS,
    BAD_COEFF
}
