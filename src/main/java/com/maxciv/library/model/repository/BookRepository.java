package com.maxciv.library.model.repository;

import com.maxciv.library.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
