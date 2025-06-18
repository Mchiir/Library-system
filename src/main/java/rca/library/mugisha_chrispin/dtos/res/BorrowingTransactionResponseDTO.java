package rca.library.mugisha_chrispin.dtos.res;


import rca.library.mugisha_chrispin.enums.TransactionStatus;

import java.time.LocalDateTime;

public class BorrowingTransactionResponseDTO {
    private Long id;
    private BookResponseDTO book;
    private String borrowerName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private TransactionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookResponseDTO getBook() {
        return book;
    }

    public void setBook(BookResponseDTO book) {
        this.book = book;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}