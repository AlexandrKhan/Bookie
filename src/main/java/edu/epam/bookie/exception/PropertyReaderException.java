package edu.epam.bookie.exception;

public class PropertyReaderException extends Exception {
    /**
     * Exception thrown by property reader
     */
    public PropertyReaderException() {
        super();
    }

    public PropertyReaderException(String message) {
        super(message);
    }

    public PropertyReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyReaderException(Throwable cause) {
        super(cause);
    }
}
