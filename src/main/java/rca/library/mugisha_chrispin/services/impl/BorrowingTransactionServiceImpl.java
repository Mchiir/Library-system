package rca.library.mugisha_chrispin.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rca.library.mugisha_chrispin.dtos.res.BookResponseDTO;
import rca.library.mugisha_chrispin.dtos.res.BorrowingTransactionResponseDTO;
import rca.library.mugisha_chrispin.exceptions.*;
import rca.library.mugisha_chrispin.models.BorrowingTransaction;
import rca.library.mugisha_chrispin.repos.BookRepository;
import rca.library.mugisha_chrispin.repos.BorrowingTransactionRepository;
import rca.library.mugisha_chrispin.services.BorrowingTransactionService;
import rca.library.mugisha_chrispin.dtos.req.BorrowingTransactionRequestDTO;
import rca.library.mugisha_chrispin.enums.AvailabilityStatus;
import rca.library.mugisha_chrispin.enums.TransactionStatus;
import rca.library.mugisha_chrispin.models.Book;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BorrowingTransactionServiceImpl implements BorrowingTransactionService {
    private final BookRepository bookRepository;
    private final BorrowingTransactionRepository transactionRepository;

    public BorrowingTransactionServiceImpl(BookRepository bookRepository, BorrowingTransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public BorrowingTransactionResponseDTO createBorrowingTransaction(BorrowingTransactionRequestDTO requestDTO) {
        log.info("Creating borrowing transaction for ISBN: {}", requestDTO.getIsbn());
        Book book = bookRepository.findByIsbn(requestDTO.getIsbn())
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + requestDTO.getIsbn() + " not found"));

        if (book.getAvailabilityStatus() == AvailabilityStatus.BORROWED) {
            throw new BookUnavailableException("Book with ISBN " + requestDTO.getIsbn() + " is currently borrowed");
        }

        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setBorrowerName(requestDTO.getBorrowerName());
        transaction.setBorrowDate(requestDTO.getBorrowDate() != null ? requestDTO.getBorrowDate() : LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);

        book.setAvailabilityStatus(AvailabilityStatus.BORROWED);
        bookRepository.save(book);

        BorrowingTransaction savedTransaction = transactionRepository.save(transaction);
        log.info("Borrowing transaction created with ID: {}", savedTransaction.getId());
        return mapToBorrowingTransactionResponseDTO(savedTransaction);
    }

    @Override
    @Transactional
    public BorrowingTransactionResponseDTO returnBook(Long transactionId, LocalDateTime returnDate) {
        log.info("Processing return for transaction ID: {}", transactionId);
        BorrowingTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with ID " + transactionId + " not found"));

        if (transaction.getStatus() == TransactionStatus.RETURNED) {
            throw new TransactionAlreadyReturnedException("Transaction with ID " + transactionId + " is already returned");
        }

        if (returnDate == null || returnDate.isBefore(transaction.getBorrowDate())) {
            throw new InvalidInputException("Return date must be after borrow date");
        }

        transaction.setReturnDate(returnDate);
        transaction.setStatus(TransactionStatus.RETURNED);

        Book book = transaction.getBook();
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        bookRepository.save(book);

        BorrowingTransaction savedTransaction = transactionRepository.save(transaction);
        log.info("Book returned for transaction ID: {}", savedTransaction.getId());
        return mapToBorrowingTransactionResponseDTO(savedTransaction);
    }

    private BorrowingTransactionResponseDTO mapToBorrowingTransactionResponseDTO(BorrowingTransaction transaction) {
        BorrowingTransactionResponseDTO responseDTO = new BorrowingTransactionResponseDTO();
        responseDTO.setId(transaction.getId());
        responseDTO.setBook(mapToBookResponseDTO(transaction.getBook()));
        responseDTO.setBorrowerName(transaction.getBorrowerName());
        responseDTO.setBorrowDate(transaction.getBorrowDate());
        responseDTO.setReturnDate(transaction.getReturnDate());
        responseDTO.setStatus(transaction.getStatus());
        return responseDTO;
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