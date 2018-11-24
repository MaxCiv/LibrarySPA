package com.maxciv.library.service;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import org.springframework.stereotype.Service;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Service
public interface LibrarianService {

    Book addBook(Book book, User owner) throws LibraryAppException;
    BookRecordOrder orderBook(Book book, User supplier) throws LibraryAppException;
    BookRecordBorrow confirmBorrowing(BookRecordBorrow recordBorrow) throws LibraryAppException;
    BookRecordBorrow closeBorrowing(BookRecordBorrow recordBorrow) throws LibraryAppException;
    BookRecordExchange confirmExchange(BookRecordExchange recordExchange) throws LibraryAppException;
    BookRecordExchange closeExchange(BookRecordExchange recordExchange) throws LibraryAppException;
    BookRecordBorrow declineBorrowing(BookRecordBorrow recordBorrow) throws LibraryAppException;
    BookRecordExchange declineExchange(BookRecordExchange recordExchange) throws LibraryAppException;
}
