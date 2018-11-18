package com.maxciv.library.model.entity;

import com.maxciv.library.model.Condition;
import com.maxciv.library.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Entity
@Table(name = "book", schema = "librarydb")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column
    private String publishYear;

    @Column
    @Enumerated
    private Status status;

    @Column
    @Enumerated
    private Condition bookCondition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
