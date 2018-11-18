package com.maxciv.library.model.repository;

import com.maxciv.library.model.entity.BookRecordOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Repository
public interface BookRecordOrderRepository extends JpaRepository<BookRecordOrder, Integer> {
    List<BookRecordOrder> findAllByBookId(int bookId);
    List<BookRecordOrder> findAllBySupplierId(int supplierId);
}
