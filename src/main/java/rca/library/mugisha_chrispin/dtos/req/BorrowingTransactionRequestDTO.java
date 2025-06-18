package rca.library.mugisha_chrispin.dtos.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class BorrowingTransactionRequestDTO {
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format")
    private String isbn;

    @NotBlank(message = "Borrower name is required")
    @Size(min = 3, max = 100, message = "Borrower name must be between 3 and 100 characters")
    private String borrowerName;

    @PastOrPresent(message = "Borrow date cannot be in the future")
    private LocalDateTime borrowDate;

    public @NotBlank(message = "ISBN is required") @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "ISBN is required") @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format") String isbn) {
        this.isbn = isbn;
    }

    public @NotBlank(message = "Borrower name is required") @Size(min = 3, max = 100, message = "Borrower name must be between 1 and 100 characters") String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(@NotBlank(message = "Borrower name is required") @Size(min = 1, max = 100, message = "Borrower name must be between 3 and 100 characters") String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public @PastOrPresent(message = "Borrow date cannot be in the future") LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(@PastOrPresent(message = "Borrow date cannot be in the future") LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }
}