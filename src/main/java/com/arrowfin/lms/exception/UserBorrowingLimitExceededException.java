package com.arrowfin.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserBorrowingLimitExceededException extends RuntimeException {
    public UserBorrowingLimitExceededException(String message) {
        super(message);
    }
}
