package com.arrowfin.lms.service;

import com.arrowfin.lms.entity.Book;
import com.arrowfin.lms.entity.BorrowingRecord;
import com.arrowfin.lms.entity.User;
import com.arrowfin.lms.repository.BookRepository;
import com.arrowfin.lms.repository.BorrowingRecordRepository;
import com.arrowfin.lms.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class BorrowingServiceImplTest {

    @InjectMocks
    private BorrowingServiceImpl borrowingService;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        User user = new User();
        user.setId(1L);
        Book book = new Book();
        book.setId(1L);
        book.setAvailable(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(new BorrowingRecord());
        borrowingService.borrowBook(1L, 1L);
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
    }
}
