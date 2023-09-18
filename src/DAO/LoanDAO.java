package DAO;

import Model.Loan;

public interface LoanDAO {

    boolean borrowBook(Loan loan);

    boolean returnBook(Loan loan);
}
