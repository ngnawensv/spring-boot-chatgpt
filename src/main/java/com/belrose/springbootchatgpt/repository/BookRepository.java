package com.belrose.springbootchatgpt.repository;

// BookRepository.java
import com.belrose.springbootchatgpt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    // You can add custom query methods here if needed
}
