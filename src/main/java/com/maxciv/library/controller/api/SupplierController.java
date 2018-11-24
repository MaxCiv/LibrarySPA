package com.maxciv.library.controller.api;

import com.maxciv.library.model.entity.BookRecordOrder;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.service.SupplierService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@RestController
@RequestMapping("api/supplier")
@PreAuthorize("hasAuthority('SUPPLIER')")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PutMapping("finishOrder")
    public BookRecordOrder finishOrder(@RequestParam(name = "orderId") BookRecordOrder recordOrder) throws LibraryAppException {
        return supplierService.finishOrder(recordOrder);
    }

    @PutMapping("denyOrder")
    public BookRecordOrder denyOrder(@RequestParam(name = "orderId") BookRecordOrder recordOrder) throws LibraryAppException {
        return supplierService.denyOrder(recordOrder);
    }
}
