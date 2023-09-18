package Service;

import DAO.LoanDAOImpl;
import Model.Loan;

import java.util.Scanner;

public class LoanService {

    private final LoanDAOImpl loanDAO;

    public LoanService(LoanDAOImpl loanDAO){
        this.loanDAO = loanDAO;

    }
    public void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Subscriber ID:");
        int subscriberID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the book ISBN:");
        int bookISBN = scanner.nextInt();


        Loan loan = new Loan(subscriberID, bookISBN, null, null);

        if(loanDAO.borrowBook(loan)){
            System.out.println("Book borrowed successfully");
        }else{
            System.out.println("Book not found");
        }
    }

    public void returnBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Subscriber ID:");
        int subscriberID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the book ISBN:");
        int bookISBN = scanner.nextInt();
        scanner.nextLine();


        Loan loan = new Loan(subscriberID, bookISBN, null, null);

        if(loanDAO.returnBook(loan)){
            System.out.println("Book returned successfully");
        }else{
            System.out.println("Book not returned");
        }
    }

//    public void showAllLoans(){}



}
