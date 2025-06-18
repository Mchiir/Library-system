package rca.library.mugisha_chrispin.exceptions;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(String message) {
        super(message);
    }
}