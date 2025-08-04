package com.arrowfin.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String libraryId;
    private String password;
    private String role; // e.g., "ROLE_USER", "ROLE_ADMIN"

    @OneToMany(mappedBy = "user")
    private Set<BorrowingRecord> borrowedBooks = new HashSet<>();
    // Getters and Setters
}