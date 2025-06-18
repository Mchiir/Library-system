package rca.library.mugisha_chrispin.exceptions;

public class TransactionAlreadyReturnedException extends RuntimeException {
    public TransactionAlreadyReturnedException(String message) {
        super(message);
    }
}