package org.meri.jpa.simplest.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "xx_authors")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public abstract class E_Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Collection<E_Book> books;

    public long getId() {
        return id;
    }

    public E_Author setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public E_Author setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<E_Book> getBooks() {
        return books;
    }
}
