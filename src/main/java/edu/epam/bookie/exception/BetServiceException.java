package edu.epam.bookie.exception;

public class BetServiceException extends Exception{
    public BetServiceException() {
        super();
    }

    public BetServiceException(String message) {
        super(message);
    }

    public BetServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetServiceException(Throwable cause) {
        super(cause);
    }
}
