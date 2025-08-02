package com.arrowfin.lms.service;

import com.arrowfin.lms.entity.Book;
import com.arrowfin.lms.entity.BorrowingRecord;
import com.arrowfin.lms.entity.User;
import com.arrowfin.lms.exception.BookNotAvailableException;
import com.arrowfin.lms.exception.ResourceNotFoundException;
import com.arrowfin.lms.exception.UserBorrowingLimitExceededException;
import com.arrowfin.lms.repository.BookRepository;
import com.arrowfin.lms.repository.BorrowingRecordRepository;
import com.arrowfin.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    private static final int BORROWING_LIMIT = 2;
    private static final int BORROWING_DAYS = 14;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public BorrowingRecord borrowBook(Long userId, Long bookId) {
        // Find the user and book, or throw an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        // Check if the book is available
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book with id " + bookId + " is not available for borrowing.");
        }

        // Check if the user has reached the borrowing limit [cite: 16]
        long currentlyBorrowed = borrowingRecordRepository.findByUserIdAndReturnDateIsNull(userId).size();
        if (currentlyBorrowed >= BORROWING_LIMIT) {
            throw new UserBorrowingLimitExceededException("User has reached the borrowing limit of " + BORROWING_LIMIT + " books.");
        }

        // Mark book as unavailable and save it [cite: 19]
        book.setAvailable(false);
        bookRepository.save(book);

        // Create and save the borrowing record
        BorrowingRecord record = new BorrowingRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(BORROWING_DAYS)); // Set due date [cite: 18]

        return borrowingRecordRepository.save(record);
    }

    @Override
    @Transactional
    public void returnBook(Long userId, Long bookId) {
        // Find the active borrowing record
        BorrowingRecord record = borrowingRecordRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId)
                .orElseThrow(() -> new ResourceNotFoundException("No active borrowing record found for this user and book."));

        // Update the return date
        record.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(record);

        // Mark the book as available again
        Book book = record.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findCurrentlyBorrowedBooksByUser(Long userId) {
        return borrowingRecordRepository.findByUserIdAndReturnDateIsNull(userId)
                .stream()
                .map(BorrowingRecord::getBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowingRecord> findBorrowingHistoryByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return borrowingRecordRepository.findByUserId(userId);
    }
}