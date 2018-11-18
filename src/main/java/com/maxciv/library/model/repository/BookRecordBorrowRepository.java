package com.maxciv.library.model.repository;

import com.maxciv.library.model.entity.BookRecordBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Repository
public interface BookRecordBorrowRepository extends JpaRepository<BookRecordBorrow, Integer> {
    List<BookRecordBorrow> findAllByBookId(int bookId);
    List<BookRecordBorrow> findAllByReaderId(int readerId);
}
