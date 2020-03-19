package org.meri.jpa.simplest.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("core")
public class E_BookImpl extends E_Book {
}
