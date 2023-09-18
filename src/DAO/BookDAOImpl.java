package DAO;

import Model.Book;
import Model.LibraryStatistics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final Connection connection;

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
    public List<Book> searchBooks(String searchCriteria) {
        List<Book> searchResults = new ArrayList<>();

        String sql = "SELECT * FROM book WHERE ISBN = ? OR title LIKE ? OR author LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int isbn;
            try {
                isbn = Integer.parseInt(searchCriteria);
                preparedStatement.setInt(1, isbn);
            } catch (NumberFormatException e) {
                isbn = -1;
                preparedStatement.setInt(1, isbn);
            }

            // Set the other two placeholders for title and author searches
            preparedStatement.setString(2, "%" + searchCriteria + "%");
            preparedStatement.setString(3, "%" + searchCriteria + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int foundIsbn = resultSet.getInt("ISBN");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, foundIsbn, author, quantity);
                searchResults.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to search for books in the database.");
        }

        return searchResults;
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
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book (ISBN, title, author, quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getQuantity());

            int rows= preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, quantity = ? WHERE ISBN = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setInt(4, book.getIsbn());

            int rows = preparedStatement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(int isbn) {
        String sql = "DELETE FROM book WHERE ISBN = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

        return 0; // Return 0 in case of an error
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

        return 0; // Return 0 in case of an error
    }

    @Override
    public LibraryStatistics getLibraryStatistics() {
        String statisticsSql = "SELECT " +
                "(SELECT COUNT(*) FROM book) AS total_books_in_library, " +
                "(SELECT COUNT(*) FROM loan WHERE returnDate IS NULL) AS total_books_borrowed, " +
                "(SELECT COUNT(*) FROM loan WHERE returnDate IS NOT NULL) AS total_books_returned, " +
                "(SELECT COUNT(*) FROM loan WHERE borrowDate < (CURRENT_DATE - INTERVAL '30 days')) AS lost";

        try (PreparedStatement statisticsStatement = connection.prepareStatement(statisticsSql);
             ResultSet statisticsResult = statisticsStatement.executeQuery()) {

            if (statisticsResult.next()) {
                int totalBooksInLibrary = statisticsResult.getInt("total_books_in_library");
                int totalBooksBorrowed = statisticsResult.getInt("total_books_borrowed");
                int totalBooksReturned = statisticsResult.getInt("total_books_returned");
                int lostBooks = statisticsResult.getInt("lost");

                return new LibraryStatistics(totalBooksInLibrary, totalBooksBorrowed, totalBooksReturned, lostBooks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve library statistics.");
        }

        return null; // Handle error case
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

        return 0; // Return 0 in case of an error
    }

    @Override
    public List<Book> getBooksAvailable() {
        List<Book> availableBooks = new ArrayList<>();
        String sql = "SELECT * FROM book where quantity > 0";

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
    public int countTotalBooksBorrowed() {
        String sql = "SELECT COUNT(*) FROM loan";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to count total borrowed books.");
        }

        return 0; // Return 0 in case of an error
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

        return 0; // Return 0 in case of an error
    }

    @Override
    public boolean doesISBNExist(int isbn) {
        String sql = "SELECT COUNT(*) FROM book WHERE ISBN = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, such as logging or throwing a custom exception
        }
        return false; // Return false in case of an error
    }

}
