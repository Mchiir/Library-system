package rca.library.mugisha_chrispin.dtos.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import rca.library.mugisha_chrispin.enums.AvailabilityStatus;

public class BookRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 3, max = 100, message = "Author must be between 3 and 100 characters")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format")
    private String isbn;

    private AvailabilityStatus availabilityStatus;

    public @NotBlank(message = "Title is required") @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author is required") @Size(min = 3, max = 100, message = "Author must be between 3 and 100 characters") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author is required") @Size(min = 3, max = 100, message = "Author must be between 3 and 100 characters") String author) {
        this.author = author;
    }

    public @NotBlank(message = "ISBN is required") @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "ISBN is required") @Pattern(regexp = "^(978|979)\\d{10}$", message = "Invalid ISBN-13 format") String isbn) {
        this.isbn = isbn;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}