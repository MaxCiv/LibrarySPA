package com.maxciv.library.controller;

import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.repository.BookRecordBorrowRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 22.11.2018
 */
@RestController
@RequestMapping("api/borrowings")
public class BorrowingsController {

    private BookRecordBorrowRepository borrowRepository;

    public BorrowingsController(BookRecordBorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @GetMapping
    public List<BookRecordBorrow> findAll() {
        return borrowRepository.findAll();
    }
}
