package org.meri.jpa.simplest.entity;

import javax.persistence.*;

@Entity
@Table(name = "xx_authors")
@Access(AccessType.FIELD)
public class E_Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public long getId() {
        return id;
    }
}
