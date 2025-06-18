package rca.library.mugisha_chrispin.services;


import rca.library.mugisha_chrispin.dtos.req.BookRequestDTO;
import rca.library.mugisha_chrispin.dtos.res.BookResponseDTO;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO getBookByIsbn(String isbn);
    String getBookAvailability(String isbn);
}