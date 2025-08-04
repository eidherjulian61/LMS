package com.arrowfin.lms.service;

import com.arrowfin.lms.dto.BookDTO;
import com.arrowfin.lms.entity.Book;
import com.arrowfin.lms.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(new Book()));
        assertEquals(1, bookService.getAllBooks().size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookDTO bookDTO = bookService.getBookById(1L);
        assertEquals("Test Book", bookDTO.getTitle());
    }

    @Test
    void testCreateBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("New Book");
        Book book = new Book();
        book.setTitle("New Book");
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        BookDTO createdBook = bookService.createBook(bookDTO);
        assertEquals("New Book", createdBook.getTitle());
    }
}
