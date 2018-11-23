package com.maxciv.library.controller.api;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.repository.BookRepository;
import com.maxciv.library.service.googlebooks.GoogleBook;
import com.maxciv.library.service.googlebooks.GoogleBooksService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author maxim.oleynik
 * @since 18.11.2018
 */
@RestController
@RequestMapping("api/book")
public class BookController {

    private BookRepository bookRepository;
    private GoogleBooksService googleBooksService;

    public BookController(BookRepository bookRepository, GoogleBooksService googleBooksService) {
        this.bookRepository = bookRepository;
        this.googleBooksService = googleBooksService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book findById(@PathVariable("id") Book book) {
        return book;
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("{id}")
    public Book update(@PathVariable("id") Book bookFromDb, @RequestBody Book book) {
        BeanUtils.copyProperties(book, bookFromDb, "id");
        return bookRepository.save(bookFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Book book) {
        bookRepository.delete(book);
    }

    @GetMapping("google/title")
    public List<GoogleBook> getGoogleBooksByTitle(@RequestParam String title) {
        return googleBooksService.getBooksByTitle(title);
    }

    @GetMapping("google/author")
    public List<GoogleBook> getGoogleBooksByAuthor(@RequestParam String author) {
        return googleBooksService.getBooksByAuthor(author);
    }
}
