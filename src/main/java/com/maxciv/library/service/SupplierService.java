package com.maxciv.library.service;

import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.exception.LibraryAppException;

/**
 * @author maxim.oleynik
 * @since 24.11.2018
 */
public interface SupplierService {

    BookRecordOrder finishOrder(BookRecordOrder recordOrder) throws LibraryAppException;
    BookRecordOrder denyOrder(BookRecordOrder recordOrder) throws LibraryAppException;
}
