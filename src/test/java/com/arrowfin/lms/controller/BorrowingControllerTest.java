package com.arrowfin.lms.controller;

import com.arrowfin.lms.entity.BorrowingRecord;
import com.arrowfin.lms.security.JwtUtil;
import com.arrowfin.lms.service.BorrowingService;
import com.arrowfin.lms.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BorrowingController.class)
class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    @WithMockUser(roles = "USER")
    void testBorrowBook() throws Exception {
        when(borrowingService.borrowBook(anyLong(), anyLong())).thenReturn(new BorrowingRecord());
        mockMvc.perform(post("/api/users/1/borrow/1").with(csrf()))
                .andExpect(status().isOk());
    }
}
