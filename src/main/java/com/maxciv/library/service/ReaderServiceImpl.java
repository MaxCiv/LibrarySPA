package com.maxciv.library.service;

import com.maxciv.library.model.Condition;
import com.maxciv.library.model.Status;
import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.model.repository.BookRecordBorrowRepository;
import com.maxciv.library.model.repository.BookRecordExchangeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author maxim.oleynik
 * @since 24.11.2018
 */
@Service
public class ReaderServiceImpl implements ReaderService {

    private final BookRecordBorrowRepository borrowRepository;
    private final BookRecordExchangeRepository exchangeRepository;

    public ReaderServiceImpl(BookRecordBorrowRepository borrowRepository, BookRecordExchangeRepository exchangeRepository) {
        this.borrowRepository = borrowRepository;
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public BookRecordBorrow borrowBook(Book book, User reader) throws LibraryAppException {
        if (book.getStatus() != Status.LIBRARY || book.getBookCondition() != Condition.IN_LIBRARY)
            throw new LibraryAppException("Failed borrowing: this book is not for borrowing.");

        List<BookRecordBorrow> bookBorrowList = borrowRepository.findAllByReaderId(reader.getId());
        if (!CollectionUtils.isEmpty(bookBorrowList)) {
            for (BookRecordBorrow bookBorrow : bookBorrowList) {
                if (bookBorrow.getBook().equals(book)
                        && bookBorrow.getReader().equals(reader)
                        && bookBorrow.getStartDate() == null)
                    throw new LibraryAppException("Failed borrowing: this book is already borrowed.");
            }
        }
        return borrowRepository.save(new BookRecordBorrow(book, reader));
    }

    @Override
    public BookRecordExchange getBookByExchange(BookRecordExchange recordExchange, User reader) throws LibraryAppException {
        if (recordExchange.getBook().getStatus() != Status.EXCHANGE
                || recordExchange.getBook().getBookCondition() != Condition.ON_EXCHANGE
                || recordExchange.getOpenExchangeDate() == null
                || recordExchange.getReader() != null)
            throw new LibraryAppException("Failed getting book by exchange: this book is not for exchange.");
        if (recordExchange.getOwner().equals(reader))
            throw new LibraryAppException("Failed getting book by exchange: it is your book, you can Close Your Exchange to get this book back.");
        recordExchange.setReader(reader);
        return exchangeRepository.save(recordExchange);
    }

    @Override
    public BookRecordExchange openExchange(BookRecordExchange recordExchange, User reader) throws LibraryAppException {
        if (recordExchange.getBook().getStatus() != Status.EXCHANGE
                || recordExchange.getBook().getBookCondition() != Condition.RETURNED_TO_OWNER
                || recordExchange.getOpenExchangeDate() != null)
            throw new LibraryAppException("Failed open exchange: this book is not for exchange.");
        if (!recordExchange.getOwner().equals(reader))
            throw new LibraryAppException("Failed open exchange: it isn't your book.");

        recordExchange.setOpenExchangeDate(new Date());
        recordExchange.getBook().setBookCondition(Condition.ON_EXCHANGE);
        return exchangeRepository.save(recordExchange);
    }

    @Override
    public BookRecordExchange closeExchangeAndTakeBook(BookRecordExchange recordExchange, User owner) throws LibraryAppException {
        if (recordExchange.getBook().getStatus() != Status.EXCHANGE
                || recordExchange.getBook().getBookCondition() != Condition.ON_EXCHANGE
                || recordExchange.getOpenExchangeDate() == null
                || recordExchange.getStartDate() != null)
            throw new LibraryAppException("Failed close exchange: you can't close this exchange.");
        if (!recordExchange.getOwner().equals(owner))
            throw new LibraryAppException("Failed close exchange: it isn't your book.");

        recordExchange.setReader(owner);
        recordExchange.setStartDate(new Date());
        recordExchange.setEndDate(new Date());
        recordExchange.getBook().setBookCondition(Condition.RETURNED_TO_OWNER);

        exchangeRepository.save(new BookRecordExchange(recordExchange.getBook(), owner));
        return exchangeRepository.save(recordExchange);
    }
}
