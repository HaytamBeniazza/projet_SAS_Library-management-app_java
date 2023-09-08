package DAO;

import DAO.BookDAO;
import Model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final Connection connection;

    // Constructor to initialize the database connection
    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int isbn = resultSet.getInt("ISBN");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, isbn, author, quantity);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve books from the database.");
        }

        return books;
    }

    @Override
    public Book getBookByISBN(int isbn) {
        String sql = "SELECT * FROM book WHERE ISBN = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                return new Book(title, isbn, author, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve book by ISBN from the database.");
        }

        return null; // Book not found
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO book (ISBN, title, author, quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add book to the database.");
        }
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, quantity = ?, isbn = ?, WHERE ISBN = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setInt(4, book.getIsbn());
            preparedStatement.setInt(5, book.getIsbn());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book in the database.");
        }
    }

    @Override
    public void deleteBook(int isbn) {
        String sql = "DELETE FROM book WHERE ISBN = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete book from the database.");
        }
    }


    @Override
    public int countTotalBooks() {
        String sql = "SELECT COUNT(*) FROM book";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to count total books.");
        }

        return 0;
    }

    @Override
    public int countBorrowedBooks() {
        String sql = "SELECT COUNT(*) FROM loan WHERE returnDate IS NULL";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to count borrowed books.");
        }

        return 0;
    }

    @Override
    public int countLostBooks() {
        String sql = "SELECT COUNT(*) FROM loan WHERE borrowDate < (CURRENT_DATE - INTERVAL '30 days')";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to count lost books.");
        }

        return 0;
    }


    @Override
    public List<Book> getBooksAvailable() {
        List<Book> availableBooks = new ArrayList<>();
        String sql = "SELECT ISBN, title, author, quantity FROM book WHERE quantity > 0";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int isbn = resultSet.getInt("ISBN");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, isbn, author, quantity);
                availableBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get available books.");
        }

        return availableBooks;
    }

    @Override
    public int countBooksBorrowedForBook(int isbn) {
        String sql = "SELECT COUNT(*) FROM loan WHERE bookIsbn = ? AND returnDate IS NULL";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to count borrowed books for ISBN: " + isbn);
        }

        return 0;
    }


    @Override
    public List<Book> searchBooks(String searchCriteria) {
        List<Book> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE title LIKE ? OR author LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + searchCriteria + "%");
            preparedStatement.setString(2, "%" + searchCriteria + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int isbn = resultSet.getInt("ISBN");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, isbn, author, quantity);
                searchResults.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to search for books in the database.");
        }

        return searchResults;
    }

    // Implement other methods like addBook, updateBook, and deleteBook here
}
