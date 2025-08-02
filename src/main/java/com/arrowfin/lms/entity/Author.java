package com.arrowfin.lms.entity;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();
    // Getters and Setters
}