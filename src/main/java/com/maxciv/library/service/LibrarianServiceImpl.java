package com.maxciv.library.service;

import com.maxciv.library.model.Condition;
import com.maxciv.library.model.Role;
import com.maxciv.library.model.Status;
import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.model.repository.BookRecordBorrowRepository;
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
    private final BookRecordBorrowRepository borrowRepository;
    private final BookRecordExchangeRepository exchangeRepository;
    private final BookRecordOrderRepository orderRepository;

    public LibrarianServiceImpl(
            BookRepository bookRepository,
            BookRecordBorrowRepository borrowRepository,
            BookRecordExchangeRepository exchangeRepository,
            BookRecordOrderRepository orderRepository
    ) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
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
        if (book.getStatus() == Status.EXCHANGE)
            exchangeRepository.save(new BookRecordExchange(book, owner));
        return book;
    }

    @Override
    public BookRecordOrder orderBook(Book book, User supplier) throws LibraryAppException {
        if (supplier == null || !supplier.getRoles().contains(Role.SUPPLIER))
            throw new LibraryAppException("User with this ID isn't a supplier.");
        if (book.getStatus() != Status.ORDER || book.getBookCondition() != Condition.NOT_AVAILABLE)
            throw new LibraryAppException("This book isn't for order.");
        book.setBookCondition(Condition.ORDER_IN_PROGRESS);
        bookRepository.save(book);
        return orderRepository.save(new BookRecordOrder(book, supplier, new Date()));
    }

    @Override
    public BookRecordBorrow confirmBorrowing(BookRecordBorrow recordBorrow) throws LibraryAppException {
        if (recordBorrow.getStartDate() != null)
            throw new LibraryAppException("Failed borrowing confirmation: this borrowing already confirmed.");
        if (recordBorrow.getBook().getBookCondition() != Condition.IN_LIBRARY || recordBorrow.getBook().getStatus() != Status.LIBRARY)
            throw new LibraryAppException("Failed borrowing confirmation: book is unavailable now.");
        recordBorrow.setStartDate(new Date());
        recordBorrow.getBook().setBookCondition(Condition.IN_READING);
        return borrowRepository.save(recordBorrow);
    }

    @Override
    public BookRecordBorrow declineBorrowing(BookRecordBorrow recordBorrow) throws LibraryAppException {
        if (recordBorrow.getStartDate() != null)
            throw new LibraryAppException("Failed borrowing decline: this borrowing already confirmed.");
        if (recordBorrow.getBook().getBookCondition() != Condition.IN_LIBRARY || recordBorrow.getBook().getStatus() != Status.LIBRARY)
            throw new LibraryAppException("Failed borrowing decline: book is unavailable now.");
        recordBorrow.setStartDate(new Date());
        recordBorrow.setEndDate(new Date());
        return borrowRepository.save(recordBorrow);
    }

    @Override
    public BookRecordBorrow closeBorrowing(BookRecordBorrow recordBorrow) throws LibraryAppException {
        if (recordBorrow.getStartDate() == null || recordBorrow.getEndDate() != null)
            throw new LibraryAppException("Failed borrowing closing: this borrowing is not for closing.");
        recordBorrow.setEndDate(new Date());
        recordBorrow.getBook().setBookCondition(Condition.IN_LIBRARY);
        return borrowRepository.save(recordBorrow);
    }

    @Override
    public BookRecordExchange confirmExchange(BookRecordExchange recordExchange) throws LibraryAppException {
        if (recordExchange.getReader() == null || recordExchange.getStartDate() != null)
            throw new LibraryAppException("Failed exchange confirmation: this exchange no confirmation needed.");
        if (recordExchange.getBook().getBookCondition() != Condition.ON_EXCHANGE || recordExchange.getBook().getStatus() != Status.EXCHANGE)
            throw new LibraryAppException("Failed exchange confirmation: book is unavailable now.");
        recordExchange.setStartDate(new Date());
        recordExchange.getBook().setBookCondition(Condition.IN_READING);
        return exchangeRepository.save(recordExchange);
    }

    @Override
    public BookRecordExchange declineExchange(BookRecordExchange recordExchange) throws LibraryAppException {
        if (recordExchange.getReader() == null || recordExchange.getStartDate() != null)
            throw new LibraryAppException("Failed exchange decline: this exchange no confirmation needed.");
        if (recordExchange.getBook().getBookCondition() != Condition.ON_EXCHANGE || recordExchange.getBook().getStatus() != Status.EXCHANGE)
            throw new LibraryAppException("Failed exchange decline: book is unavailable now.");
        recordExchange.setStartDate(new Date());
        recordExchange.setEndDate(new Date());
        exchangeRepository.save(new BookRecordExchange(recordExchange.getBook(), recordExchange.getOwner(), new Date()));
        return exchangeRepository.save(recordExchange);
    }

    @Override
    public BookRecordExchange closeExchange(BookRecordExchange recordExchange) throws LibraryAppException {
        if (recordExchange.getStartDate() == null || recordExchange.getEndDate() != null)
            throw new LibraryAppException("Failed exchange closing: this exchange no closing needed.");
        recordExchange.setEndDate(new Date());
        recordExchange.getBook().setBookCondition(Condition.ON_EXCHANGE);
        exchangeRepository.save(new BookRecordExchange(recordExchange.getBook(), recordExchange.getOwner(), new Date()));
        return exchangeRepository.save(recordExchange);
    }
}
