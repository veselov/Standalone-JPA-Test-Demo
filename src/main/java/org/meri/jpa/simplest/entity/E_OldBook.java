package org.meri.jpa.simplest.entity;

import javax.persistence.*;

@Entity
@Table(name = "xx_old_books")
@Access(AccessType.FIELD)
public class E_OldBook {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @ManyToOne
    private E_OldAuthor author;

    private String name;

    public long getId() {
        return id;
    }

    public E_OldAuthor getAuthor() {
        return author;
    }

    public E_OldBook setAuthor(E_OldAuthor author) {
        this.author = author;
        return this;
    }

    public String getName() {
        return name;
    }

    public E_OldBook setName(String name) {
        this.name = name;
        return this;
    }


}
