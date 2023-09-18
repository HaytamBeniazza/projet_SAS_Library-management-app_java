package DAO;

import Model.Book;
import Model.LibraryStatistics;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    List<Book> searchBooks(String searchCriteria);
    Book getBookByISBN(int isbn);
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(int isbn);
    int countTotalBooks();
    int countBorrowedBooks();
    LibraryStatistics getLibraryStatistics();
    int countLostBooks();
    List<Book> getBooksAvailable();
    int countTotalBooksBorrowed();
    int countBooksBorrowedForBook(int isbn);

    boolean doesISBNExist(int isbn);
}
