package edu.epam.bookie.exception;

public class UserDaoException extends Exception {
    public UserDaoException() {
        super();
    }

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDaoException(Throwable cause) {
        super(cause);
    }
}
