package rca.library.mugisha_chrispin.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.library.mugisha_chrispin.models.BorrowingTransaction;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
}