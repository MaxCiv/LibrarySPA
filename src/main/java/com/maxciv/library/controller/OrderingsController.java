package com.maxciv.library.controller;

import com.maxciv.library.model.entity.BookRecordOrder;
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
@RequestMapping("api/orderings")
public class OrderingsController {

    private BookRecordOrderRepository orderRepository;

    public OrderingsController(BookRecordOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<BookRecordOrder> findAll() {
        return orderRepository.findAll();
    }
}
