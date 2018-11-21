package com.maxciv.library.controller;

import com.maxciv.library.model.entity.Book;
import com.maxciv.library.model.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
    public void update(@PathVariable("id") Book book) {
        bookRepository.delete(book);
    }
}
