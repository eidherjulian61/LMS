package com.arrowfin.lms.service;

import com.arrowfin.lms.entity.Book;
import com.arrowfin.lms.entity.BorrowingRecord;
import java.util.List;

public interface BorrowingService {

    /**
     * Handles the logic for a user to borrow a book.
     *
     * @param userId The ID of the user borrowing the book.
     * @param bookId The ID of the book to be borrowed.
     * @return The created BorrowingRecord.
     */
    BorrowingRecord borrowBook(Long userId, Long bookId);

    /**
     * Handles the logic for a user to return a book.
     *
     * @param userId The ID of the user returning the book.
     * @param bookId The ID of the book being returned.
     */
    void returnBook(Long userId, Long bookId);

    /**
     * Retrieves a list of books currently borrowed by a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of borrowed Books.
     */
    List<Book> findCurrentlyBorrowedBooksByUser(Long userId);

    /**
     * Retrieves the complete borrowing history for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of all borrowing records for the user.
     */
    List<BorrowingRecord> findBorrowingHistoryByUser(Long userId);
}