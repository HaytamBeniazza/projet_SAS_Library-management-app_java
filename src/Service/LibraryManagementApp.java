package Service;


import DAO.*;
import Model.Librarian;
import java.sql.Connection;
import java.util.Scanner;

public class LibraryManagementApp {
    private final BookService bookService;
    private final LoanService loanService;
    private final AuthService authService;
    private final UserService userService;
    private final LibrarianService librarianService;

    private Connection connection;

    public LibraryManagementApp() {
        Connection connection = new DatabaseConnection().getConnection();
        bookService = new BookService(new BookDAOImpl(connection));
        userService = new UserService(new UserDAOImpl(connection));
        authService = new AuthService(new LibrarianDAOImpl(connection));
        librarianService = new LibrarianService(new LibrarianDAOImpl(connection));
        loanService = new LoanService(new LoanDAOImpl(connection));
    }

    public void start(){
        while(true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");

            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                int mainChoice = scanner.nextInt();
                scanner.nextLine();
                if (mainChoice == 1) {
                    Librarian authenticateLibrarian = authService.auth(scanner);

                    if (authenticateLibrarian != null) {
                        System.out.println("Welcome " + authenticateLibrarian.getName());
                        menu(authenticateLibrarian);
                        break;
                    } else {
                        System.out.println("Invalid Credentials");
                    }
                } else if (mainChoice == 2) {
                    break;
                }
            }
        }


    }

    public void menu(Librarian authenticateLibrarian) {
            Scanner scanner = new Scanner(System.in);
            boolean loggedIn = false;
        while(!loggedIn) {
            while(true) {
                System.out.println("1. Add a Book");
                System.out.println("2. Update an Existing Book");
                System.out.println("3. Delete a Book From The Library");
                System.out.println("4. Search a Book");
                System.out.println("5. Show All Available Books");
                System.out.println("6. Add a New User");
                System.out.println("7. Borrow a book");
                System.out.println("8. Return a Book");
                System.out.println("9. Show Statistics");
                System.out.println("0. Logout");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> bookService.addBook();
                        case 2 -> bookService.updateBook();
                        case 3 -> bookService.deleteBook();
                        case 4 -> bookService.searchBook();
                        case 5 -> bookService.getAvailableBooks();
                        case 6 -> librarianOrUser();
                        case 7 -> loanService.borrowBook();
                        case 8 -> loanService.returnBook();
                        case 9 -> bookService.displayLibraryStatistics();
                        case 0 -> {
                            System.out.println("Logging Out");
                            loggedIn = true;
                        }
                        default -> System.out.println("Please choose one of the option.");
                    }
                    break;
                } else {
                    System.out.println("Please choose one of the option.");
                    scanner.nextLine();
                    break;
                }
            }
        }

    }

    public void librarianOrUser(){

        System.out.println("1. Librarian");
        System.out.println("2. User");

        Scanner scanner = new Scanner(System.in);
            int mainChoice = scanner.nextInt();
//            scanner.nextLine();
            if (mainChoice == 1) {
                librarianService.addLibrarian();
            } else if (mainChoice == 2) {
                userService.addUser();
            }else{
                System.out.println("Please enter a valid input");
            }
    }
}