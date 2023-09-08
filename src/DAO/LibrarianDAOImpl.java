package DAO;

import Model.Librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class LibrarianDAOImpl implements LibrarianDAO{

    private final Connection connection;

    public LibrarianDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void addLibrarian(Librarian librarian) {
        String sql = "INSERT INTO librarian (name, code, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, librarian.getName());
            preparedStatement.setString(2, librarian.getCode());
            preparedStatement.setString(3, librarian.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add a librarian.");
        }
    }

    @Override
    public Optional<Librarian> getLibrarianByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Librarian> authenticateLibrarian(String email, String code) {
        return Optional.empty();
    }

    @Override
    public boolean doesEmailExist(String email) {
        return false;
    }

    @Override
    public boolean doesCodeExist(String code) {
        return false;
    }

    @Override
    public boolean isValidEmail(String email) {
        return false;
    }
}
