package com.maxciv.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Entity
@Table(name = "librarybooks", schema = "librarydb")
@Getter
@Setter
@NoArgsConstructor
public class BookRecordBorrow {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private User reader;

    @Column
    private Date startDate;

    @Column
    private Date endDate;
}
