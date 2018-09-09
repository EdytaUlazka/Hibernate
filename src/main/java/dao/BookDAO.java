package dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedQueries;
import util.HibernateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public void create(Book book) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();

        } catch (Exception e) {


        } finally {

        }
    }


    public void update(Book book) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(book);// skrót CTRL +SHIFT +  /-robienie adnotacji
/*        Book bookPersisted=session.find(Book.class, book.getId());
        bookPersisted.setTitle(book.getTitle());
        bookPersisted.setAuthor(book.getAuthor());
        bookPersisted.setPublished(book.getPublished());
        bookPersisted.setBookType(book.getBookType());*/
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void remove(Book book) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();//pobieranie session factory
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();//tworzymy transakcję, bo będziemy coś zmieniać w bazie
        //gdybyśmy tylko pobierali, to nie musimy tworzyć transakcji
        try {
            session.remove(book);
            transaction.commit();//o ile skommitujemy transakcje
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Book> showBooks() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();//pobieranie session factory
        Session session = sessionFactory.openSession();
        List<Book> books = new ArrayList<>();
        try {
            String query = "select b from Book b ";
            books = session
                    .createQuery(query, Book.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return books;
    }

    public List<Book> findAllPublishedAfter(int year) {
        //LocalDate.of(year, 1, 1);
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Book> books = new ArrayList<>();
        try {
            String query = "select b from Book b " +
                    "where b.published > :published";
            books = session.createQuery(query, Book.class)
                    .setParameter("published", LocalDate.of(year, 1, 1))
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return books;
    }


    public List<Object[]> findAuthorWithManyBooks() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        List<Object[]> objects = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String query = "select b.author, count(b.title) from Book b " +
                    "group by b.author " +
                    "having count(b.title) > 1";
            objects = session.createQuery(query).getResultList();
        }
        return objects;
    }


    public List<Book> findByTitle(String title) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        List<Book> books=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

           books = session.createNamedQuery("Book.findByTitle", Book.class)
                    .setParameter("title", title)
                    .getResultList();
        }
        return books;
    }
}
