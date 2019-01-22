package org.meri.jpa.simplest;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.meri.jpa.AbstractTestCase;
import org.meri.jpa.simplest.entity.Person;

import static org.junit.Assert.*;

public class DemoJPATest extends AbstractTestCase {

  private static final String CHANGELOG_LOCATION = "src/test/java/org/meri/jpa/simplest/db.changelog.xml";
  private static EntityManagerFactory factory;

  public DemoJPATest() {
  }

  @Override
  protected String getInitialChangeLog() {
    return CHANGELOG_LOCATION;
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testJPA() {
    EntityManager em = factory.createEntityManager();

    Query query = em.createQuery("SELECT x FROM Person x");
    List<Person> allUsers = query.getResultList();
    em.close();

    assertFalse(allUsers.isEmpty());
  }

  @Test
  public void boolLiteral() {

    EntityManager em = factory.createEntityManager();

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Person> cq = cb.createQuery(Person.class);

    Root<Person> r = cq.from(Person.class);
    cq.select(r);
    cq.where(cb.equal(r.get("woo"), cb.literal(Boolean.FALSE)));

    TypedQuery<Person> tq = em.createQuery(cq);

    assertTrue(!tq.getResultList().isEmpty());

  }

  @BeforeClass
  public static void createFactory() {
    factory = Persistence.createEntityManagerFactory("Simplest");
  }

  @AfterClass
  public static void closeFactory() {
    factory.close();
  }

}
