package com.maxciv.library.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LibraryAppException extends Exception {

    public LibraryAppException(String message) {
        super(message);
    }

    public LibraryAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
