package com.maxciv.library.service;

import com.maxciv.library.model.Condition;
import com.maxciv.library.model.Status;
import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.model.repository.BookRecordOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    private final BookRecordOrderRepository orderRepository;

    public SupplierServiceImpl(BookRecordOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public BookRecordOrder finishOrder(BookRecordOrder recordOrder) throws LibraryAppException {
        if (recordOrder.getEndDate() != null)
            throw new LibraryAppException("Failed finishing: this order already finishing.");
        recordOrder.setEndDate(new Date());
        recordOrder.getBook().setStatus(Status.LIBRARY);
        recordOrder.getBook().setBookCondition(Condition.IN_LIBRARY);
        return orderRepository.save(recordOrder);
    }

    @Override
    public BookRecordOrder denyOrder(BookRecordOrder recordOrder) throws LibraryAppException {
        if (recordOrder.getEndDate() != null)
            throw new LibraryAppException("Failed denial: this order already finishing.");
        recordOrder.setEndDate(new Date());
        recordOrder.getBook().setBookCondition(Condition.NOT_AVAILABLE);
        return orderRepository.save(recordOrder);
    }
}
