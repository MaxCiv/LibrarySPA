package com.maxciv.library.controller;

import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.repository.BookRecordExchangeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 22.11.2018
 */
@RestController
@RequestMapping("api/exchanges")
public class ExchangesController {

    private BookRecordExchangeRepository exchangeRepository;

    public ExchangesController(BookRecordExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @GetMapping
    public List<BookRecordExchange> findAll() {
        return exchangeRepository.findAll();
    }
}
