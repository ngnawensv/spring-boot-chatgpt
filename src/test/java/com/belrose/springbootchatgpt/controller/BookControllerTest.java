package com.belrose.springbootchatgpt.controller;

import com.belrose.springbootchatgpt.model.Book;
import com.belrose.springbootchatgpt.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1");
        Book book2 = new Book(2L, "Title2", "Author2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].author").value("Author2"));

        verify(bookService, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void getBookById() throws Exception {
        Long bookId = 1L;
        Book book = new Book(bookId, "Title", "Author");

        when(bookService.getBookById(bookId)).thenReturn(book);

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"));

        verify(bookService, times(1)).getBookById(bookId);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void saveBook() throws Exception {
        Book bookToSave = new Book(null, "New Title", "New Author");
        Book savedBook = new Book(1L, "New Title", "New Author");

        when(bookService.saveBook(any(Book.class))).thenReturn(savedBook);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookToSave)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.author").value("New Author"));

        verify(bookService, times(1)).saveBook(any(Book.class));
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void deleteBook() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(bookId);
        verifyNoMoreInteractions(bookService);
    }
}

