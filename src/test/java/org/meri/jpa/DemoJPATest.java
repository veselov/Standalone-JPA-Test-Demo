package org.meri.jpa;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.meri.jpa.simplest.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
  public void testJPA() {

    EntityManager em = factory.createEntityManager();

    em.getTransaction().begin();

    E_Author author = new E_Author();
    em.persist(author);
    long id = author.getId();

    em.getTransaction().commit();

    em = factory.createEntityManager();

    em.getTransaction().begin();

    author = em.find(E_Author.class, id);
    em.remove(author);
    em.flush();

    em.persist(author);
    em.flush();
    em.flush();

    em.getTransaction().rollback();
    em.close();


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
