package Service;

import DAO.LibrarianDAO;
import DAO.LibrarianDAOImpl;
import DAO.UserDAO;
import Model.Librarian;

import java.util.Scanner;

public class AuthService {
    private final LibrarianDAOImpl librarianDAO;

    public AuthService(LibrarianDAOImpl librarianDAO) {
        this.librarianDAO = librarianDAO;
    }

    public Librarian auth(Scanner scanner) {
        System.out.println("Sign in:");
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        if (!librarianDAO.doesEmailExist(email)) {
            System.out.println("Please enter a valid email address");
        } else {
            System.out.println("Enter your password:");
            String code = scanner.nextLine();
            if (!librarianDAO.doesCodeExist(code)) {
                System.out.println("Please enter a valid password");
            } else {
                Librarian authenticated = librarianDAO.authenticateLibrarian(email, code);
                return authenticated;
            }
        }
        return null;
    }
}
