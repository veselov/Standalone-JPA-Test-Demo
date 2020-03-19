package org.meri.jpa.simplest.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("core")
public class E_AuthorImpl extends E_Author {
}
