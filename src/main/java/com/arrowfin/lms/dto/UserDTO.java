package com.arrowfin.lms.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String libraryId;
    private String password;
    private String role;
}
