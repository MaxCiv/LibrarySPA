package com.maxciv.library.service;

import com.maxciv.library.model.entity.Book;
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
}
