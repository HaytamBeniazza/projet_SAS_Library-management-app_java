package DAO;

import Model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    List<Book> searchBooks(String searchCriteria);
    Book getBookByISBN(int isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int isbn);
    int countTotalBooks();
    int countBorrowedBooks();
    int countLostBooks();
    List<Book> getBooksAvailable();
    int countBooksBorrowedForBook(int isbn);
}
