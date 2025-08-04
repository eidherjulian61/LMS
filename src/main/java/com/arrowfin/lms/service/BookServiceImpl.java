package com.arrowfin.lms.service;

import com.arrowfin.lms.dto.BookDTO;
import com.arrowfin.lms.entity.Author;
import com.arrowfin.lms.entity.Book;
import com.arrowfin.lms.exception.ResourceNotFoundException;
import com.arrowfin.lms.repository.AuthorRepository;
import com.arrowfin.lms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return convertToDTO(book);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        return convertToDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setAvailable(bookDTO.isAvailable());
        if (bookDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(bookDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookDTO.getAuthorId()));
            book.setAuthor(author);
        }
        return convertToDTO(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> searchBooks(String title, String author, String isbn) {
        if (StringUtils.hasText(title)) {
            return bookRepository.findByTitleContaining(title).stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        if (StringUtils.hasText(author)) {
            return bookRepository.findByAuthorNameContaining(author).stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        if (StringUtils.hasText(isbn)) {
            return bookRepository.findByIsbn(isbn).stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAvailable(book.isAvailable());
        if (book.getAuthor() != null) {
            bookDTO.setAuthorId(book.getAuthor().getId());
            bookDTO.setAuthorName(book.getAuthor().getName());
        }
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setAvailable(bookDTO.isAvailable());
        if (bookDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(bookDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookDTO.getAuthorId()));
            book.setAuthor(author);
        }
        return book;
    }
}
