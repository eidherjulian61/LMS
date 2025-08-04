package com.arrowfin.lms.repository;

import com.arrowfin.lms.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findByUserIdAndReturnDateIsNull(Long userId);

    List<BorrowingRecord> findByUserId(Long userId);

    Optional<BorrowingRecord> findByUserIdAndBookIdAndReturnDateIsNull(Long userId, Long bookId);
}