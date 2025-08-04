package com.arrowfin.lms.service;

import com.arrowfin.lms.dto.AuthorDTO;
import com.arrowfin.lms.entity.Author;
import com.arrowfin.lms.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(new Author()));
        assertEquals(1, authorService.getAllAuthors().size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Test Author");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        AuthorDTO authorDTO = authorService.getAuthorById(1L);
        assertEquals("Test Author", authorDTO.getName());
    }

    @Test
    void testCreateAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("New Author");
        Author author = new Author();
        author.setName("New Author");
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
        assertEquals("New Author", createdAuthor.getName());
    }
}
