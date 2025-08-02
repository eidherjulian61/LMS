package com.arrowfin.lms.repository;

import com.arrowfin.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLibraryId(String libraryId);
}