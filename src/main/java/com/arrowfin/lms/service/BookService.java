package com.arrowfin.lms.service;

import com.arrowfin.lms.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);

    List<BookDTO> searchBooks(String title, String author, String isbn);
}
