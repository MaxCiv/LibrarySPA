package com.maxciv.library.controller.api;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.model.repository.BookRepository;
import com.maxciv.library.model.repository.UserRepository;
import com.maxciv.library.service.LibrarianService;
import com.maxciv.library.service.UserService;
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

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final LibrarianService librarianService;

    public LibrarianController(
            UserRepository userRepository,
            BookRepository bookRepository,
            UserService userService,
            LibrarianService librarianService
    ) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.librarianService = librarianService;
    }

    @PostMapping("addBook")
    public Book add(@RequestBody Book book, @RequestParam(name = "ownerId") User owner) throws LibraryAppException {
        return librarianService.addBook(book, owner);
    }

    @PutMapping("orderBook")
    public BookRecordOrder order(@RequestParam(name = "book") Book book, @RequestParam(name = "supplierId") User supplier) throws LibraryAppException {
        return librarianService.orderBook(book, supplier);
    }
}
