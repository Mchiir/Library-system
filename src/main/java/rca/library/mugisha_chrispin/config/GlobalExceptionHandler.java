package rca.library.mugisha_chrispin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rca.library.mugisha_chrispin.exceptions.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBookNotFoundException(BookNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<Map<String, String>> handleBookUnavailableException(BookUnavailableException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, String>> handleInvalidInputException(InvalidInputException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTransactionNotFoundException(TransactionNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionAlreadyReturnedException.class)
    public ResponseEntity<Map<String, String>> handleTransactionAlreadyReturnedException(TransactionAlreadyReturnedException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        return buildErrorResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return new ResponseEntity<>(errorResponse, status);
    }
}