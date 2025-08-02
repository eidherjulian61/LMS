package com.arrowfin.lms.repository;

import com.arrowfin.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthorNameContaining(String authorName);
    Optional<Book> findByIsbn(String isbn);
}