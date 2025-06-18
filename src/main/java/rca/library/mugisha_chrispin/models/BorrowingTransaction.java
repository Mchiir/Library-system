package rca.library.mugisha_chrispin.models;

import jakarta.persistence.*;
import rca.library.mugisha_chrispin.enums.TransactionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrowing_transaction")
public class BorrowingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrower_name", nullable = false, length = 100)
    private String borrowerName;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    public BorrowingTransaction() {}
    public BorrowingTransaction(Book book, String borrowerName, LocalDateTime borrowDate, TransactionStatus status) {
        this.book = book;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
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