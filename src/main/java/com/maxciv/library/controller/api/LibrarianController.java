package com.maxciv.library.controller.api;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordBorrow;
import com.maxciv.library.model.entity.BookRecordExchange;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.service.LibrarianService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@RestController
@RequestMapping("api/librarian")
@PreAuthorize("hasAuthority('LIBRARIAN')")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping("addBook")
    public Book addBook(@RequestBody Book book, @RequestParam(name = "ownerId") User owner) throws LibraryAppException {
        return librarianService.addBook(book, owner);
    }

    @PutMapping("orderBook")
    public BookRecordOrder orderBook(
            @RequestParam(name = "bookId") Book book,
            @RequestParam(name = "supplierId") User supplier
    ) throws LibraryAppException {
        return librarianService.orderBook(book, supplier);
    }

    @PutMapping("confirmBorrowing")
    public BookRecordBorrow confirmBorrowing(@RequestParam(name = "borrowId") BookRecordBorrow recordBorrow) throws LibraryAppException {
        return librarianService.confirmBorrowing(recordBorrow);
    }

    @PutMapping("declineBorrowing")
    public BookRecordBorrow declineBorrowing(@RequestParam(name = "borrowId") BookRecordBorrow recordBorrow) throws LibraryAppException {
        return librarianService.declineBorrowing(recordBorrow);
    }

    @PutMapping("closeBorrowing")
    public BookRecordBorrow closeBorrowing(@RequestParam(name = "borrowId") BookRecordBorrow recordBorrow) throws LibraryAppException {
        return librarianService.closeBorrowing(recordBorrow);
    }

    @PutMapping("confirmExchange")
    public BookRecordExchange confirmExchange(@RequestParam(name = "exchangeId") BookRecordExchange recordExchange) throws LibraryAppException {
        return librarianService.confirmExchange(recordExchange);
    }

    @PutMapping("declineExchange")
    public BookRecordExchange declineExchange(@RequestParam(name = "exchangeId") BookRecordExchange recordExchange) throws LibraryAppException {
        return librarianService.declineExchange(recordExchange);
    }

    @PutMapping("closeExchange")
    public BookRecordExchange closeExchange(@RequestParam(name = "exchangeId") BookRecordExchange recordExchange) throws LibraryAppException {
        return librarianService.closeExchange(recordExchange);
    }
}
