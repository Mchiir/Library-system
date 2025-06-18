package rca.library.mugisha_chrispin.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rca.library.mugisha_chrispin.dtos.req.BorrowingTransactionRequestDTO;
import rca.library.mugisha_chrispin.services.BorrowingTransactionService;
import rca.library.mugisha_chrispin.dtos.res.BorrowingTransactionResponseDTO;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/transactions")
public class BorrowingTransactionController {
    @Autowired
    private BorrowingTransactionService transactionService;

    @PostMapping
    public ResponseEntity<BorrowingTransactionResponseDTO> createBorrowingTransaction(
            @Valid @RequestBody BorrowingTransactionRequestDTO requestDTO) {
        BorrowingTransactionResponseDTO responseDTO = transactionService.createBorrowingTransaction(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}/return")
    public ResponseEntity<BorrowingTransactionResponseDTO> returnBook(
            @PathVariable Long transactionId, @RequestBody LocalDateTime returnDate) {
        BorrowingTransactionResponseDTO responseDTO = transactionService.returnBook(transactionId, returnDate);
        return ResponseEntity.ok(responseDTO);
    }
}