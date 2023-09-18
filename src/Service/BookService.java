package Service;

import DAO.BookDAO;
import DAO.BookDAOImpl;
import DAO.DatabaseConnection;
import Model.Book;
import Model.LibraryStatistics;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private final BookDAOImpl bookDAO;


    public BookService(BookDAOImpl bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void addBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book Title:");
        String title = scanner.nextLine();

        System.out.print("Enter Book Author:");
        String author = scanner.nextLine();

        System.out.print("Enter Book ISBN:");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Book Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, isbn, author, quantity);


        if(bookDAO.addBook(newBook)){
            System.out.println("Book Added Successfully");
        }else{
            System.out.println("Book Not Added");
        }

    }

    public void deleteBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ISBN:");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        if(bookDAO.deleteBook(isbn)){
            System.out.println("Book Deleted Successfully");
        }else{
            System.out.println("Book Not Deleted");
        }
    }

    public void updateBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the book ISBN to update:");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Book Title:");
        String title = scanner.nextLine();

        System.out.print("New Book Author:");
        String author = scanner.nextLine();

        System.out.print("New Book Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, isbn, author, quantity);

        if(bookDAO.updateBook(newBook)){
            System.out.println("Book Updated Successfully");
        }else{
            System.out.println("Book Not Updated");
        }

    }

    public void searchBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Search by title author or isbn:");
        String search = scanner.nextLine();

        List<Book> books = bookDAO.searchBooks(search);
        if(books.isEmpty()){
            System.out.println("No Books Found");
        }else{
            System.out.println("Books Found:");

            for(Book book : books){
                System.out.println(book.toString());
            }
        }
    }

    public void getAvailableBooks() {
        List<Book> books = bookDAO.getBooksAvailable();

        if (books.isEmpty()) {
            System.out.println("There are no available books.");
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
    }


    public void displayLibraryStatistics() {
        Connection connection = DatabaseConnection.getConnection();

// Create a BookDAO instance by passing the connection to BookDAOImpl
        BookDAO bookDAO = new BookDAOImpl(connection); // Instantiate your BookDAO implementation
        LibraryStatistics libraryStatistics = bookDAO.getLibraryStatistics();
        int totalBooksBorrowed = bookDAO.countTotalBooksBorrowed();

        if (libraryStatistics != null) {
            // Display or use the library statistics
            System.out.println("Total Books in Library: " + libraryStatistics.getTotalBooksInLibrary());
            System.out.println("Total Books Borrowed: " + libraryStatistics.getTotalBooksBorrowed());
            System.out.println("Total Books Returned: " + libraryStatistics.getTotalBooksReturned());
            System.out.println("Lost Books: " + libraryStatistics.getLostBooks());
            System.out.println("Total Books Borrowed: " + totalBooksBorrowed);
        } else {
            System.out.println("Failed to retrieve library statistics.");
        }
    }

}

