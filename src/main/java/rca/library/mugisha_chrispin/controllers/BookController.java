package rca.library.mugisha_chrispin.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.library.mugisha_chrispin.dtos.req.BookRequestDTO;
import rca.library.mugisha_chrispin.dtos.res.BookResponseDTO;
import rca.library.mugisha_chrispin.services.BookService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO responseDTO = bookService.createBook(bookRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        if (!isbn.matches("\\d{13}")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid ISBN: must be exactly 13 digits with no spaces."));
        }

        BookResponseDTO responseDTO = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{isbn}/availability")
    public ResponseEntity<String> getBookAvailability(@PathVariable String isbn) {
        if (!isbn.matches("\\d{13}")) {
            return ResponseEntity.badRequest().body("Invalid ISBN: must be exactly 13 digits with no spaces.");
        }

        String availability = bookService.getBookAvailability(isbn);
        return ResponseEntity.ok(availability);
    }
}