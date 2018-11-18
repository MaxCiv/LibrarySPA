package com.maxciv.library.model.repository;

import com.maxciv.library.model.entity.BookRecordExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Repository
public interface BookRecordExchangeRepository extends JpaRepository<BookRecordExchange, Integer> {
    List<BookRecordExchange> findAllByBookId(int bookId);
    List<BookRecordExchange> findAllByOwnerId(int ownerId);
    List<BookRecordExchange> findAllByReaderId(int readerId);
}
