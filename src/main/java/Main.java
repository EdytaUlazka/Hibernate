import dao.BookDAO;
import entity.Book;
import entity.BookType;
import service.BookService;
import util.HibernateUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

       // HibernateUtils.getSessionFactory();
        BookService bookService=new BookService();
        Book book1=new Book();
        book1.setTitle("Hobbit");
        book1.setAuthor("Tolkien");
        book1.setPublished(LocalDate.of(1950,1, 12));
        book1.setBookType(BookType.THRILLER);

        Book book2=new Book();
        book2.setTitle("Krzyzacy");
        book2.setAuthor("Sienkiewicz");
        book2.setPublished(LocalDate.of(1892,4, 12));
        book2.setBookType(BookType.COMEDY);

        Book book3=new Book();
        book3.setTitle("Harry Potter");
        book3.setAuthor("Rowling");
        book3.setPublished(LocalDate.of(2002,12, 1));
        book3.setBookType(BookType.FANTASY);


        Book book4=new Book();
        book4.setTitle("Hobbit2");
        book4.setAuthor("Tolkien");
        book4.setPublished(LocalDate.of(1950,1, 12));
        book4.setBookType(BookType.THRILLER);

        Book book5=new Book();
        book5.setTitle("Krzyzacy2");
        book5.setAuthor("Sienkiewicz");
        book5.setPublished(LocalDate.of(1892,4, 12));
        book5.setBookType(BookType.COMEDY);

        Book book6=new Book();
        book6.setTitle("Harry Potter2");
        book6.setAuthor("Rowling");
        book6.setPublished(LocalDate.of(2002,12, 1));
        book6.setBookType(BookType.FANTASY);


        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);
        bookService.addBook(book6);

//book3.setTitle("Harry POtter i Kamień");
//bookService.editBook(book3);

//Book book7=new Book();
//        book7.setTitle("aaa");
//        book7.setAuthor("ffff");
//
//bookService.removeBook(book3);
//bookService.editBook(book4);

//Book boookToDelete=new Book();
//boookToDelete.setId(2L);
//
//bookService.removeBook(boookToDelete);


     //   bookService.findAndDeleteBooksAfter(2000);

//bookService.listAllAuthorsWithManyBooks();
bookService.findByTitle("Harry POtter i Kamień");
    }
}

//Lazy loading-pobiera tylko te dane, które są potrzebne
//Eager loading - pobiera wszystkie
