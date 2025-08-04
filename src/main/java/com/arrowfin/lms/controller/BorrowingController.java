package com.arrowfin.lms.controller;

import com.arrowfin.lms.entity.Book;
import com.arrowfin.lms.entity.BorrowingRecord;
import com.arrowfin.lms.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/users/{userId}/borrow/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        // Users can borrow up to 2 books
        // Books can only be borrowed if available
        BorrowingRecord record = borrowingService.borrowBook(userId, bookId);
        return ResponseEntity.ok(record);
    }

    @PutMapping("/users/{userId}/return/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> returnBook(@PathVariable Long userId, @PathVariable Long bookId) {
        borrowingService.returnBook(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/books")
    public ResponseEntity<List<Book>> getBorrowedBooks(@PathVariable Long userId) {
        List<Book> books = borrowingService.findBorrowedBooksByUser(userId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/users/{userId}/history")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingHistory(@PathVariable Long userId) {
        List<BorrowingRecord> history = borrowingService.findBorrowingHistoryByUser(userId);
        return ResponseEntity.ok(history);
    }
}