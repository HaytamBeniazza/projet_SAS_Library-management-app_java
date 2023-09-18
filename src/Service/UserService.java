package Service;

import DAO.UserDAOImpl;
import Model.User;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;


public class UserService {

    private final UserDAOImpl userDAO;

//    boolean addUser(User user);
//    Optional<User> getUserByEmail(String email);
//    boolean doesEmailExist(String email);

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Librarian's Name:");
            String name = scanner.nextLine();
            System.out.print("Enter Librarian's Email:");
            String email = scanner.nextLine();
            System.out.print("Enter Librarian's Phone:");
            int phone = scanner.nextInt();
            scanner.nextLine();

            User newUser = new User(name, email, phone);

            if(userDAO.addUser(newUser)){
                System.out.println("User added successfully");
            }else{
                System.out.println("User not added");
            }
    }
}
