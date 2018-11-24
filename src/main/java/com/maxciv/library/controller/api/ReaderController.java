package com.maxciv.library.controller.api;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.service.ReaderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@RestController
@RequestMapping("api/reader")
@PreAuthorize("hasAuthority('READER')")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("borrowBook")
    public BookRecordBorrow borrowBook(
            @AuthenticationPrincipal User reader,
            @RequestParam(name = "bookId") Book book
    ) throws LibraryAppException {
        return readerService.borrowBook(book, reader);
    }

    @PutMapping("getBookByExchange")
    public BookRecordExchange getBookByExchange(
            @AuthenticationPrincipal User reader,
            @RequestParam(name = "exchangeId") BookRecordExchange recordExchange
    ) throws LibraryAppException {
        return readerService.getBookByExchange(recordExchange, reader);
    }

    @PutMapping("openExchange")
    public BookRecordExchange openExchange(
            @AuthenticationPrincipal User reader,
            @RequestParam(name = "exchangeId") BookRecordExchange recordExchange
    ) throws LibraryAppException {
        return readerService.openExchange(recordExchange, reader);
    }

    @PutMapping("closeExchange")
    public BookRecordExchange closeExchangeAndTakeBook(
            @AuthenticationPrincipal User owner,
            @RequestParam(name = "exchangeId") BookRecordExchange recordExchange
    ) throws LibraryAppException {
        return readerService.closeExchangeAndTakeBook(recordExchange, owner);
    }
}
