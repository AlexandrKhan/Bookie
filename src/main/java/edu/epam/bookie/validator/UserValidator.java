package edu.epam.bookie.validator;

public class UserValidator {
    private static final String USERNAME_REGEX = "[a-zA-Z0-9]+([_-]?[a-zA-Z0-9]+){8,40}";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9@#$%!]{8,40}";
    private static final String EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private static UserValidator INSTANCE = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }

    public boolean isUsername(String username) {
        return username.matches(USERNAME_REGEX);
    }

    public boolean isPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public boolean isEmail(String email) {
        return email.matches(PASSWORD_REGEX);
    }
}
