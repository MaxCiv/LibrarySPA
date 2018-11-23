package com.maxciv.library.controller.api;

import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.repository.BookRecordBorrowRepository;
import com.maxciv.library.model.repository.BookRecordExchangeRepository;
import com.maxciv.library.model.repository.BookRecordOrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 22.11.2018
 */
@RestController
@RequestMapping("api")
public class BookRecordsController {

    private final BookRecordBorrowRepository borrowRepository;
    private final BookRecordExchangeRepository exchangeRepository;
    private final BookRecordOrderRepository orderRepository;

    public BookRecordsController(
            BookRecordBorrowRepository borrowRepository,
            BookRecordExchangeRepository exchangeRepository,
            BookRecordOrderRepository orderRepository
    ) {
        this.borrowRepository = borrowRepository;
        this.exchangeRepository = exchangeRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("borrowings")
    public List<BookRecordBorrow> findAllBorrowings() {
        return borrowRepository.findAll();
    }

    @GetMapping("exchanges")
    public List<BookRecordExchange> findAllExchanges() {
        return exchangeRepository.findAll();
    }

    @GetMapping("orderings")
    public List<BookRecordOrder> findAllOrderings() {
        return orderRepository.findAll();
    }
}
