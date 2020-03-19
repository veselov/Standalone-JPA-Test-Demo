package org.meri.jpa.simplest.entity;

import javax.persistence.*;

@Entity
@Table(name = "xx_books")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public abstract class E_Book {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @ManyToOne
    private E_Author author;

    private String name;

    public long getId() {
        return id;
    }

    public E_Author getAuthor() {
        return author;
    }

    public E_Book setAuthor(E_Author author) {
        this.author = author;
        return this;
    }

    public String getName() {
        return name;
    }

    public E_Book setName(String name) {
        this.name = name;
        return this;
    }
}

