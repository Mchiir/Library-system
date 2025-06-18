package rca.library.mugisha_chrispin.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.library.mugisha_chrispin.models.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}