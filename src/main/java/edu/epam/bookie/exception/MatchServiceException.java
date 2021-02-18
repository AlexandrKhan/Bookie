package edu.epam.bookie.exception;

public class MatchServiceException extends Exception{
    public MatchServiceException() {
        super();
    }

    public MatchServiceException(String message) {
        super(message);
    }

    public MatchServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchServiceException(Throwable cause) {
        super(cause);
    }


}
