package com.maxciv.library.service;

import com.maxciv.library.model.Condition;
import com.maxciv.library.model.Role;
import com.maxciv.library.model.Status;
import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.model.repository.BookRecordExchangeRepository;
import com.maxciv.library.model.repository.BookRecordOrderRepository;
import com.maxciv.library.model.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Service
public class LibrarianServiceImpl implements LibrarianService {

    private final BookRepository bookRepository;
    private final BookRecordExchangeRepository exchangeRepository;
    private final BookRecordOrderRepository orderRepository;

    public LibrarianServiceImpl(
            BookRepository bookRepository,
            BookRecordExchangeRepository exchangeRepository,
            BookRecordOrderRepository orderRepository) {
        this.bookRepository = bookRepository;
        this.exchangeRepository = exchangeRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Book addBook(Book book, User owner) throws LibraryAppException {
        switch (book.getStatus()) {
            case LIBRARY:
                book.setBookCondition(Condition.IN_LIBRARY);
                break;
            case EXCHANGE:
                book.setBookCondition(Condition.RETURNED_TO_OWNER);
                break;
            case ORDER:
                book.setBookCondition(Condition.NOT_AVAILABLE);
                break;
        }
        if (book.getStatus() == Status.EXCHANGE && owner == null) {
            throw new LibraryAppException("This book must have an owner.");
        }
        bookRepository.save(book);

        if (book.getStatus() == Status.EXCHANGE) {
            exchangeRepository.save(new BookRecordExchange(book, owner));
        }
        return book;
    }

    @Override
    public BookRecordOrder orderBook(Book book, User supplier) throws LibraryAppException {
        if (supplier == null || !supplier.getRoles().contains(Role.SUPPLIER))
            throw new LibraryAppException("User with this ID isn't a supplier.");

        if (book.getStatus() != Status.ORDER || book.getBookCondition() != Condition.NOT_AVAILABLE) {
            throw new LibraryAppException("This book isn't for order.");
        }

        book.setBookCondition(Condition.ORDER_IN_PROGRESS);
        bookRepository.save(book);

        return orderRepository.save(new BookRecordOrder(book, supplier, new Date()));
    }
}
