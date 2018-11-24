package com.maxciv.library.service;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import org.springframework.stereotype.Service;

/**
 * @author maxim.oleynik
 * @since 24.11.2018
 */
@Service
public interface ReaderService {

    BookRecordBorrow borrowBook(Book book, User reader) throws LibraryAppException;
    BookRecordExchange getBookByExchange(BookRecordExchange recordExchange, User reader) throws LibraryAppException;
    BookRecordExchange openExchange(BookRecordExchange recordExchange, User reader) throws LibraryAppException;
    BookRecordExchange closeExchangeAndTakeBook(BookRecordExchange recordExchange, User owner) throws LibraryAppException;
}
