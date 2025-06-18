package rca.library.mugisha_chrispin.models;

import jakarta.persistence.*;
import rca.library.mugisha_chrispin.enums.AvailabilityStatus;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false)
    private AvailabilityStatus availabilityStatus;


    public Book() {}
    public Book(String title, String author, String isbn, AvailabilityStatus availabilityStatus) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availabilityStatus = availabilityStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}