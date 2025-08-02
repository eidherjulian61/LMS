package com.arrowfin.lms.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(unique = true)
    private String isbn;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    // Getters and Setters
}