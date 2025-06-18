package rca.library.mugisha_chrispin.services;



import rca.library.mugisha_chrispin.dtos.req.BorrowingTransactionRequestDTO;
import rca.library.mugisha_chrispin.dtos.res.BorrowingTransactionResponseDTO;

import java.time.LocalDateTime;

public interface BorrowingTransactionService {
    BorrowingTransactionResponseDTO createBorrowingTransaction(BorrowingTransactionRequestDTO requestDTO);
    BorrowingTransactionResponseDTO returnBook(Long transactionId, LocalDateTime returnDate);
}