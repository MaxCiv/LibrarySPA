package com.maxciv.library.service.googlebooks;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoogleBooksService {

    List<GoogleBook> getBooksByTitle(String query);
    List<GoogleBook> getBooksByAuthor(String query);
}
