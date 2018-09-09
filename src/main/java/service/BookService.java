package service;

import dao.BookDAO;
import entity.Book;

import java.util.List;

public class BookService {
    BookDAO bookDAO=new BookDAO();

    public void addBook(Book book){
       bookDAO.create(book);
    }
    public void editBook(Book book){
        bookDAO.update(book);
    }
    public void removeBook(Book book){
        bookDAO.remove(book);
    }
    public void findAndDeleteBooksAfter(int year) {
        List<Book> booksToDelete = bookDAO.findAllPublishedAfter(year);
        System.out.println("Usunę nast. książki: ");

        /* for(Book book : booksToDelete) {
             System.out.println(book);
             bookDAO.remove(book);
         }*/

        booksToDelete.forEach(e -> {
            System.out.println(e);
            bookDAO.remove(e);
        });

    }
    public void listAllAuthorsWithManyBooks(){
        List<Object[]> objects=bookDAO.findAuthorWithManyBooks();
        objects.forEach(ob->{
            System.out.println(ob[0]+"->"+ob[1]);
        });
    }


    public void findByTitle(String title){
   List<Book> books=bookDAO.findByTitle(title);
   books.forEach(System.out::println);

    }

}