package com.belrose.springbootchatgpt.service;

// BookService.java
import com.belrose.springbootchatgpt.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Long id);
}

