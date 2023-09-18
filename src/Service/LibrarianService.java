package Service;

import DAO.BookDAO;
import DAO.BookDAOImpl;
import DAO.LibrarianDAO;
import DAO.LibrarianDAOImpl;
import Model.Librarian;

import java.sql.Connection;
import java.util.Scanner;

public class LibrarianService {

//    private final BookService bookService;
//    private final LibrarianService librarianService;
//    private final Connection connection;
    private final LibrarianDAOImpl librarianDAO;



    public LibrarianService(LibrarianDAOImpl librarianDAO){
        this.librarianDAO = librarianDAO;
    }




    public void addLibrarian(){
        Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Librarian's Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Librarian's Email: ");
            String code = scanner.nextLine();
            System.out.print("Enter Librarian's Password: ");
            String email = scanner.nextLine();

            Librarian newLibrarian = new Librarian(name, code, email);

            if(librarianDAO.addLibrarian(newLibrarian)){
                System.out.println("Librarian added successfully");
            }else{
                System.out.println("Librarian not added");
            }
    }
}
