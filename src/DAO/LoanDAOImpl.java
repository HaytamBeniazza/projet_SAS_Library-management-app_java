package DAO;

import Model.Loan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanDAOImpl implements LoanDAO {
    private final Connection connection;

    public LoanDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean borrowBook(Loan loan) {
        String sql = "INSERT INTO loan (subscriberId, bookIsbn, borrowDate, returnDate) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loan.getSubscriberId());
            preparedStatement.setInt(2, loan.getBookIsbn());
            preparedStatement.setDate(3, getCurrentDate());
            preparedStatement.setDate(4, null);
            int rows = preparedStatement.executeUpdate();
            return rows > 0; // Check for rows > 0, not rows > 1
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean returnBook(Loan loan) {
        String sql = "UPDATE loan SET returnDate = ? WHERE subscriberId = ? AND bookIsbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, getCurrentDate());
            preparedStatement.setInt(2, loan.getSubscriberId());
            preparedStatement.setInt(3,  loan.getBookIsbn());
            int rows = preparedStatement.executeUpdate();
            return rows > 0; // Check for rows > 0
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Date getCurrentDate() {
        java.util.Date date = new java.util.Date();
        return new Date(date.getTime());
    }
}
