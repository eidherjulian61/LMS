package com.arrowfin.lms.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private boolean available;
    private Long authorId;
    private String authorName;
}
