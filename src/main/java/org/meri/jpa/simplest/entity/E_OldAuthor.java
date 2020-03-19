package org.meri.jpa.simplest.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "xx_old_authors")
@Access(AccessType.FIELD)
public class E_OldAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Collection<E_OldBook> books;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public E_OldAuthor setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<E_OldBook> getBooks() {
        if (books == null) { books = new ArrayList<>(); }
        return books;
    }


}
