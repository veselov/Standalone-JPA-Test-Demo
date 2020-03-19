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

    em.createQuery("delete from E_Book b").executeUpdate();
    em.createQuery("delete from E_Author a").executeUpdate();
    em.createQuery("delete from E_OldBook b").executeUpdate();
    em.createQuery("delete from E_OldAuthor a").executeUpdate();

    E_Author author = new E_AuthorImpl();
    author.setName("Lois McMaster Bujold");
    em.persist(author);

    E_Book book = new E_BookImpl();
    book.setAuthor(author);
    book.setName("Falling Free");
    em.persist(book);

    book = new E_BookImpl();
    book.setAuthor(author);
    book.setName("The Vor Game");
    em.persist(book);

    E_OldAuthor oldAuthor = new E_OldAuthor();
    oldAuthor.setName("Lois McMaster Bujold");
    em.persist(oldAuthor);

    E_OldBook oldBook = new E_OldBook();
    oldBook.setAuthor(oldAuthor);
    oldBook.setName("Falling Free");
    em.persist(oldBook);

    oldBook = new E_OldBook();
    oldBook.setAuthor(oldAuthor);
    oldBook.setName("The Vor Game");
    em.persist(oldBook);

    em.getTransaction().commit();
    em.getTransaction().begin();

    em.flush();
    em.clear();

    for (E_OldAuthor a : em.createQuery("select a from E_OldAuthor a", E_OldAuthor.class).getResultList()) {
      System.out.println("Old Author "+a.getName()+" ("+a.getId()+"," + a.getClass().getName()+")");
      for (E_OldBook b : a.getBooks()) {
        System.out.println("  Book "+b.getName()+" ("+b.getId()+"," + b.getClass().getName()+")");
      }
    }

    for (E_Author a : em.createQuery("select a from E_Author a", E_Author.class).getResultList()) {
      System.out.println("Author "+a.getName()+" ("+a.getId()+"," + a.getClass().getName()+")");
      for (E_Book b : a.getBooks()) {
        System.out.println("  Book "+b.getName()+" ("+b.getId()+"," + b.getClass().getName()+")");
      }
    }

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
