package rca.library.mugisha_chrispin.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rca.library.mugisha_chrispin.dtos.req.BookRequestDTO;
import rca.library.mugisha_chrispin.dtos.res.BookResponseDTO;
import rca.library.mugisha_chrispin.enums.AvailabilityStatus;
import rca.library.mugisha_chrispin.exceptions.BookNotFoundException;
import rca.library.mugisha_chrispin.exceptions.InvalidInputException;
import rca.library.mugisha_chrispin.models.Book;
import rca.library.mugisha_chrispin.repos.BookRepository;
import rca.library.mugisha_chrispin.services.BookService;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        log.info("Creating book with ISBN: {}", bookRequestDTO.getIsbn());
        bookRepository.findByIsbn(bookRequestDTO.getIsbn())
                .ifPresent(book -> {
                    throw new InvalidInputException("Book with ISBN " + bookRequestDTO.getIsbn() + " already exists");
                });

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setAvailabilityStatus(bookRequestDTO.getAvailabilityStatus() != null ?
                bookRequestDTO.getAvailabilityStatus() : AvailabilityStatus.AVAILABLE);

        Book savedBook = bookRepository.save(book);
        log.info("Book created with ID: {}", savedBook.getId());
        return mapToBookResponseDTO(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDTO getBookByIsbn(String isbn) {
        log.info("Retrieving book with ISBN: {}", isbn);
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
        return mapToBookResponseDTO(book);
    }

    @Override
    @Transactional(readOnly = true)
    public String getBookAvailability(String isbn) {
        log.info("Checking availability for book with ISBN: {}", isbn);
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
        return book.getAvailabilityStatus().name();
    }

    private BookResponseDTO mapToBookResponseDTO(Book book) {
        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(book.getId());
        responseDTO.setTitle(book.getTitle());
        responseDTO.setAuthor(book.getAuthor());
        responseDTO.setIsbn(book.getIsbn());
        responseDTO.setAvailabilityStatus(book.getAvailabilityStatus());
        return responseDTO;
    }
}